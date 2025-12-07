package com.estefani.appviajes.serviciosJPAImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.estefani.appviajes.RESTcontrollers.datos.ResumenPedido;
import com.estefani.appviajes.constantes.Estados;
import com.estefani.appviajes.constantesSQL.ConstantesSQL;
import com.estefani.appviajes.model.Carrito;
import com.estefani.appviajes.model.Pedido;
import com.estefani.appviajes.model.ProductoPedido;
import com.estefani.appviajes.model.Usuario;
import com.estefani.appviajes.servicios.ServicioCarrito;
import com.estefani.appviajes.servicios.ServicioPedidos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
@Transactional
public class ServicioPedidoImpl implements ServicioPedidos{
	
	@Autowired
	private ServicioCarrito servicioCarrito;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	
	@Override
	public List<Pedido> obtenerPedidos() {
		//esto es java pql
		return entityManager.createQuery("select p from Pedido p order by p.id desc").getResultList();
		
	}

	@Override
	public Pedido obtenPedidoPorId(int idPedido) {
		
		return (Pedido)entityManager.find(Pedido.class, idPedido);
	}

	@Override
	public void actualizarPedido(int idPedido, String estado) {
		Pedido p = entityManager.find(Pedido.class, idPedido);
		p.setEstado(estado);
		entityManager.merge(p);
		
	}
	
	@Override
	public void confirmarPedido(int idUsuario) {
		Pedido p = obtenerPedidoIncompletoActual(idUsuario);
		//obtener los productos en el carrito para meterlos en productoPedido
		Usuario u = entityManager.find(Usuario.class, idUsuario);
		List<Carrito> c = entityManager.createQuery(
				"select c from Carrito c where c.usuario.id = :usuario_id")
				.setParameter("usuario_id", idUsuario)
				.getResultList();
		System.out.println("productos en el carrito a procesar: " + c.size());
		for( Carrito productoCarrito : c ) {
			ProductoPedido pp = new ProductoPedido();
			pp.setCantidad(productoCarrito.getCantidad());
			pp.setViaje(productoCarrito.getViaje());
			pp.setPedido(p);
			entityManager.persist(pp);
		}
		p.setEstado(Estados.TERMINADO.name());
		entityManager.merge(p);
		//eliminar los productos del carrito
		entityManager.createNativeQuery(ConstantesSQL.SQL_VACIAR_CARRITO)
		.setParameter("usuario_id", idUsuario).executeUpdate();
		
	}

	@Override
	public void procesarPaso1(String nombre, String direccion, String provincia, String codigoPostal, String email, String movil, int idUsuario) {
		Pedido p = obtenerPedidoIncompletoActual(idUsuario);
		p.setNombreCompleto(nombre);
		p.setDireccion(direccion);
		p.setProvincia(provincia);
		p.setCodigoPostal(codigoPostal);
		p.setEmail(email);
		p.setMovil(movil);
		entityManager.merge(p);
		
	}

	@Override
	public void procesarPaso2(String tarjeta, String numero, String titular, int idUsuario) {
		Pedido p = obtenerPedidoIncompletoActual(idUsuario);
		p.setTipoTarjeta(tarjeta);
		p.setNumeroTarjeta(numero);
		p.setTitularTarjeta(titular);
		entityManager.merge(p);		
		//preparamos el ResumenPedido a devolver
			
	}
	
	@Override
	public ResumenPedido procesarPaso3(String comentario, int idUsuario) {
		Pedido p = obtenerPedidoIncompletoActual(idUsuario);
		p.setComentario(comentario);
		
		
		entityManager.merge(p);		
		//preparamos el ResumenPedido a devolver
		return obtenerResumenDelPedido(idUsuario);	
	}
	
	private ResumenPedido obtenerResumenDelPedido(int idUsuario) {
		Pedido p = obtenerPedidoIncompletoActual(idUsuario);
		ResumenPedido resumen = new ResumenPedido();
		resumen.setNombreCompleto(p.getNombreCompleto());
		resumen.setDireccion(p.getDireccion());
		resumen.setProvincia(p.getProvincia());
		resumen.setCodigoPostal(p.getCodigoPostal());
		resumen.setEmail(p.getEmail());
		resumen.setMovil(p.getMovil());
		resumen.setComentario(p.getComentario());
		resumen.setTipoTarjeta(p.getTipoTarjeta());
		resumen.setTitularTarjeta(p.getTitularTarjeta());
		resumen.setNumeroTarjeta(p.getNumeroTarjeta());
		resumen.setViajes(servicioCarrito.obtenerViajesCarrito(idUsuario));
		return resumen;
	}
	
	
	//aqui se esta usando JPQL
		private Pedido obtenerPedidoIncompletoActual(int idUsuario) {
			Usuario usuario = entityManager.find(Usuario.class, idUsuario);
			Object pedidoIncompleto = null;
			List<Pedido> pedidos = 
					entityManager.createQuery(
						"select p from Pedido p where p.estado = :estado and p.usuario.id = :usuario_id")
					.setParameter("estado", Estados.INCOMPLETO.name())
					.setParameter("usuario_id", idUsuario).getResultList();
			if(pedidos.size() == 1) {
				pedidoIncompleto = pedidos.get(0);
			}
			Pedido pedido = null;
			if(pedidoIncompleto != null) {
				pedido = (Pedido) pedidoIncompleto;
			}else {
				pedido = new Pedido();
				pedido.setEstado(Estados.INCOMPLETO.name());
				pedido.setUsuario(usuario);
			}
			return pedido;
		}

		

		

		

		

		

}

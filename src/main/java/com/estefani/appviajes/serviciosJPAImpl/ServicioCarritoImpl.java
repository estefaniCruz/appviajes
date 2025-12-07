package com.estefani.appviajes.serviciosJPAImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.estefani.appviajes.constantesSQL.ConstantesSQL;
import com.estefani.appviajes.model.Carrito;
import com.estefani.appviajes.model.Usuario;
import com.estefani.appviajes.model.Viaje;
import com.estefani.appviajes.servicios.ServicioCarrito;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.Tuple;
import jakarta.persistence.TupleElement;

@Service
@Transactional
public class ServicioCarritoImpl implements ServicioCarrito {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void agregarViaje(int idUsuario, long idViaje, int cantidad) {
		//aunque seria mas facil lanzar una sql
		Usuario usuario = (Usuario) entityManager.find(Usuario.class, idUsuario);
		List<Carrito> viajesCarrito = usuario.getViajesCarrito();
		//quiero ver si el usuario ya compro ese carrito y en tal caso incrementar su cantidad
		boolean viajeEnCarrito = false;
		//recorreremos todos los viajes que hay en el carrito
		for(Carrito pc : viajesCarrito) {
			if (pc.getViaje().getId() == idViaje) {
				viajeEnCarrito = true;
				pc.setCantidad(pc.getCantidad() + cantidad);
				//actualizar este producto con merge y entity
				entityManager.merge(pc); //actualizamos el registro en carrito
			}
		}
		//si el producto no esta en el carrito pues crear un registro nuevo en el carrito
		if (! viajeEnCarrito ) {
			Carrito viajeCarrito = new Carrito();
			viajeCarrito.setUsuario(usuario);
			viajeCarrito.setViaje(entityManager.find(Viaje.class, idViaje));
			viajeCarrito.setCantidad(cantidad);
			entityManager.persist(viajeCarrito);
			
		}
			
	}//end agregarViaje

	@Override
	public List<Map<String, Object>> obtenerViajesCarrito(int idUsuario) {
		Query query = 
				entityManager.createNativeQuery(
						ConstantesSQL.SQL_OBTENER_VIAJES_CARRITO, Tuple.class);
		query.setParameter("usuario_id", idUsuario);
		List<Tuple> tuples = query.getResultList();

		List<Map<String, Object>> resultado = new ArrayList<>();
		for (Tuple tuple : tuples) {
		    Map<String, Object> fila = new HashMap<>();
		    for (TupleElement<?> element : tuple.getElements()) {
		        fila.put(element.getAlias(), tuple.get(element));
		    }
		    resultado.add(fila);
		}
		return resultado;
	}

	@Override
	public void quitarViajeCarrito(int idUsuario, Long idViaje) {
		entityManager.createNativeQuery(ConstantesSQL.SQL_ELIMINAR_VIAJE_CARRITO).
		setParameter("usuario_id", idUsuario).setParameter("viaje_id", idViaje).executeUpdate();
		
	}
	
	
	

}

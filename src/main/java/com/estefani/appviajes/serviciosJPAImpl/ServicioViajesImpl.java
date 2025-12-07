package com.estefani.appviajes.serviciosJPAImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.query.sql.internal.NativeQueryImpl;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.stereotype.Service;


import com.estefani.appviajes.constantesSQL.ConstantesSQL;
import com.estefani.appviajes.model.Categoria;
import com.estefani.appviajes.model.Viaje;
import com.estefani.appviajes.servicios.ServicioViajes;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.Tuple;
import jakarta.persistence.TupleElement;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ServicioViajesImpl implements ServicioViajes {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void registrarViaje(Viaje viaje) {
		try {
			viaje.setImagenPortada(viaje.getImagen().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//asociarle la categoria  a nivel de bases de datos
		//porque tiene @Transient
		Categoria categoria = entityManager.find(Categoria.class, viaje.getIdCategoria());
		viaje.setCategoria(categoria);
		entityManager.persist(viaje);
	}

	@Override
	public List<Viaje> obtenerViajes() {
		// se pone como el objeto literal
		return entityManager.createQuery("select v from Viaje v").getResultList();
	}

	@Override
	public void borrarViaje(long id) {
		// antes de borrar el producto debemos borrar las referencias
		// al mismo en el carrito y pedidos
		// hacer esto mismo en usuario
		entityManager.createNativeQuery("delete from CARRITO where VIAJE_ID = :id ").setParameter("id", id)
				.executeUpdate();

		//
		entityManager.createNativeQuery("delete from PRODUCTO_PEDIDO where VIAJE_ID = :id ").setParameter("id", id)
				.executeUpdate();

		// lanza sql
		entityManager.createNativeQuery("delete from tabla_viajes where id = :id ").setParameter("id", id)
				.executeUpdate();

	}

	@Override
	public void actualizarViaje(Viaje viajeFormEditar) {
		Viaje viajeBaseDeDatos = entityManager.find(Viaje.class, viajeFormEditar.getId());
		viajeBaseDeDatos.setEpoca(viajeFormEditar.getEpoca());
		viajeBaseDeDatos.setDestino(viajeFormEditar.getDestino());
		viajeBaseDeDatos.setNoches(viajeFormEditar.getNoches());
		viajeBaseDeDatos.setTipo_alojamiento(viajeFormEditar.getTipo_alojamiento());
		viajeBaseDeDatos.setDescripcion(viajeFormEditar.getDescripcion());
		viajeBaseDeDatos.setPrecio(viajeFormEditar.getPrecio());
		viajeBaseDeDatos.setActivo(viajeFormEditar.isActivo());
		if (viajeFormEditar.getImagen().getSize() > 0) {
			try {
				viajeBaseDeDatos.setImagenPortada(viajeFormEditar.getImagen().getBytes());
			} catch (IOException e) {
				System.out.println("no se pudo procesar el archivo subido");
				e.printStackTrace();
			}
		}
		viajeBaseDeDatos.setCategoria(entityManager.find(Categoria.class, viajeFormEditar.getIdCategoria()));
		entityManager.merge(viajeBaseDeDatos);

	}

	@Override
	public Viaje obtenerViajePorId(long id) {

		return entityManager.find(Viaje.class, id);
	}

	@Override
	public List<Map<String, Object>> obtenerViajesParaFormarJSON() {
		Query query = entityManager.createNativeQuery(ConstantesSQL.SQL_OBTENER_VIAJES_PARA_JSON, Tuple.class);
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
	public List<Map<String, Object>> obtenerViajesFiltradosParaFormarJSON(String estacion) {
		
		Query query = entityManager.createNativeQuery(ConstantesSQL.SQL_OBTENER_VIAJES_POR_EPOCAS_JSON, Tuple.class).setParameter("epoca", estacion);
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

}

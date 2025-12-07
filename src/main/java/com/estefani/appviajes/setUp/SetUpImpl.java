package com.estefani.appviajes.setUp;

import java.io.IOException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.estefani.appviajes.model.Carrito;
import com.estefani.appviajes.model.Categoria;
import com.estefani.appviajes.model.Usuario;
import com.estefani.appviajes.model.Viaje;
import com.estefani.appviajes.servicios.ServicioPedidos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
@Transactional
public class SetUpImpl implements SetUp {
	
	@Autowired
	private ServicioPedidos servicioPedidos;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void prepararRegistros() {
		TablaSetUp registroSetUp = null;

		try {
			registroSetUp = (TablaSetUp) entityManager.createQuery("select r from TablaSetUp r").getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("no se encontro ningun registro en la tabla setUp,"
					+ "comenzamos a realizar los registros iniciales...");
		}
		if (registroSetUp != null && registroSetUp.isCompletado()) {
			return;

		}
		
		//preparamos categorias iniciales:
		Categoria cAventura = new Categoria("Aventura", "Experiencias extremas");
		entityManager.persist(cAventura);
		Categoria cNaturaleza = new Categoria("Naturaleza", "Paisajes salvajes y conexion con la naturaleza");
		entityManager.persist(cNaturaleza);
		Categoria cCultura = new Categoria("Cultura","Sumergete en la historia y en sus tradiciones");
		entityManager.persist(cCultura);
		Categoria cRelax = new Categoria("Relax", "Destinos paradisíacos, sol y descanso absoluto.");
		entityManager.persist(cRelax);
		
	
		//preparamos los registros iniciales:
		Viaje viaje1 = new Viaje("verano", "japon", 5, "estudio", "centrico", 500, true);
		viaje1.setImagenPortada(obtenerInfoImagen("http://localhost:8080/imagenes_base/viajes/japon.jpg"));
		viaje1.setCategoria(cCultura);
		entityManager.persist(viaje1);

		Viaje viaje2 = new Viaje("invierno", "canadá", 7, "bungalow", "periférico", 650, false);
		viaje2.setImagenPortada(obtenerInfoImagen("http://localhost:8080/imagenes_base/viajes/canada.jpg"));
		viaje2.setCategoria(cAventura);
		entityManager.persist(viaje2);

		Viaje viaje3 = new Viaje("primavera", "italia", 4, "chalet", "histórico", 480, true);
		viaje3.setImagenPortada(obtenerInfoImagen("http://localhost:8080/imagenes_base/viajes/italia.jpg"));
		viaje3.setCategoria(cCultura);
		entityManager.persist(viaje3);

		Viaje viaje4 = new Viaje("otoño", "perú", 6, "chalet", "rural", 520, true);
		viaje4.setImagenPortada(obtenerInfoImagen("http://localhost:8080/imagenes_base/viajes/peru.jpg"));
		viaje4.setCategoria(cNaturaleza);
		entityManager.persist(viaje4);

		Viaje viaje5 = new Viaje("verano", "australia", 10, "hotel", "costero", 900, false);
		viaje5.setImagenPortada(obtenerInfoImagen("http://localhost:8080/imagenes_base/viajes/australia.jpg"));
		viaje5.setCategoria(cRelax);
		entityManager.persist(viaje5);

		Viaje viaje6 = new Viaje("primavera", "noruega", 3, "cabaña", "remoto", 430, true);
		viaje6.setImagenPortada(obtenerInfoImagen("http://localhost:8080/imagenes_base/viajes/noruega.jpg"));
		viaje6.setCategoria(cNaturaleza);
		entityManager.persist(viaje6);

		Viaje viaje7 = new Viaje("invierno", "laponia", 4, "cabaña", "polar", 590, true);
		viaje7.setImagenPortada(obtenerInfoImagen("http://localhost:8080/imagenes_base/viajes/laponia.jpg"));
		viaje7.setCategoria(cAventura);
		entityManager.persist(viaje7);

		Viaje viaje8 = new Viaje("verano", "lanzarote", 3, "atico", "volcánico", 450, true);
		viaje8.setImagenPortada(obtenerInfoImagen("http://localhost:8080/imagenes_base/viajes/lanzarote.jpg"));
		viaje8.setCategoria(cRelax);
		entityManager.persist(viaje8);

		Usuario usuario1 = new Usuario("Estefani", "cruz", "1234", "estefanitovardelacruz@gmail.com", "611680412");
		entityManager.persist(usuario1);

		Usuario usuario2 = new Usuario("Carlos", "Ramírez", "abcd", "carlos.ramirez@gmail.com", "612345678");
		entityManager.persist(usuario2);

		Usuario usuario3 = new Usuario("Lucía", "Martínez", "pass123", "lucia.martinez@gmail.com", "613456789");
		entityManager.persist(usuario3);

		Usuario usuario4 = new Usuario("Miguel", "Santos", "qwerty", "miguel.santos@gmail.com", "614567890");
		entityManager.persist(usuario4);

		Usuario usuario5 = new Usuario("Elena", "Gómez", "elena2025", "elena.gomez@gmail.com", "615678901");
		entityManager.persist(usuario5);

		Usuario usuario6 = new Usuario("Javier", "López", "javilopez", "javier.lopez@gmail.com", "616789012");
		entityManager.persist(usuario6);

		// el usuario 1 mete a su carrito el libro 2:
		// vamos a meter unos productos en el carrito de varios usuarios
		Carrito registroCarrito = new Carrito();
		registroCarrito.setUsuario(usuario1);
		registroCarrito.setViaje(viaje1);
		registroCarrito.setCantidad(3);
		entityManager.persist(registroCarrito);
		
		//
		servicioPedidos.procesarPaso1("Estefani", "ponferrada 31", "Madrid", "28029", "estefani@gmail.com", "611680412", usuario1.getId());
		servicioPedidos.procesarPaso2("1", "0123 4567 8901 0123", "Estefani", usuario1.getId());
		servicioPedidos.procesarPaso3("Paquetes de viajes divertidos", usuario1.getId());
		servicioPedidos.confirmarPedido(usuario1.getId());
		
		Carrito registroCarrito2 = new Carrito();
		registroCarrito2.setUsuario(usuario3);
		registroCarrito2.setViaje(viaje6);
		registroCarrito2.setCantidad(2);
		entityManager.persist(registroCarrito2);

		TablaSetUp registro = new TablaSetUp();
		registro.setCompletado(true);
		entityManager.persist(registro);

	}

	// metodo que nos va a permitir obtener un byte[] de cada archivo
	// de imagenes bye
	private byte[] obtenerInfoImagen(String ruta_origen) {
		byte[] info = null;
		try {
			URL url = new URL(ruta_origen);

			info = org.apache.commons.io.IOUtils.toByteArray(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block

			System.out.println("no se pudo procesar: " + ruta_origen);
			e.printStackTrace();
		}

		return info;

	}
}

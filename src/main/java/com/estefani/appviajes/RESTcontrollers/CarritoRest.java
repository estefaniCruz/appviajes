package com.estefani.appviajes.RESTcontrollers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.estefani.appviajes.servicios.ServicioCarrito;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("carritoREST")
public class CarritoRest {
	@Autowired
	private ServicioCarrito servicioCarrito;
	
	@RequestMapping("eliminar")
	public String eliminar(@RequestParam("id") Long id, HttpServletRequest request) {
		int idUsuario = 
				(int)request.getSession().getAttribute("usuario_id");
		servicioCarrito.quitarViajeCarrito(idUsuario, id);
		return "ok";
	}
	
	@RequestMapping("obtener")
	public List<Map<String, Object>> obtener(HttpServletRequest request) {
		int idUsuario = 
				(int)request.getSession().getAttribute("usuario_id");

		return servicioCarrito.obtenerViajesCarrito(idUsuario);
	}
	
	@RequestMapping("agregarViaje")
	public String agregarViaje(Integer id, Integer cantidad, HttpServletRequest request) {
		int idUsuario = 
				(int) request.getSession().getAttribute("usuario_id");
		System.out.println("ya tenemos todo lo necesario para agregar un producto al carrito");
		System.out.println("idUsuario " + idUsuario);
		System.out.println("idViaje " + id);
		System.out.println("cantidad " + cantidad);
		servicioCarrito.agregarViaje(idUsuario, id, cantidad);
		return "ok, todo ha ido bien";
	}

}

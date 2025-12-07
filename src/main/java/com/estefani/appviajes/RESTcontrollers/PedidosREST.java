package com.estefani.appviajes.RESTcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estefani.appviajes.RESTcontrollers.datos.ResumenPedido;
import com.estefani.appviajes.servicios.ServicioPedidos;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("pedidosREST/")
public class PedidosREST {
	
	@Autowired
	private ServicioPedidos servicioPedidos;
	
	@RequestMapping("paso4")
	public String paso3(HttpServletRequest request) {
		int idUsuario = 
				(int)request.getSession().getAttribute("usuario_id");
		servicioPedidos.confirmarPedido(idUsuario);
		return "ok";
	}
	
	@RequestMapping("paso3")
	public ResumenPedido paso3(String comentario,
			HttpServletRequest request) {
		int idUsuario = 
				(int)request.getSession().getAttribute("usuario_id");
		ResumenPedido resumen = 
				servicioPedidos.procesarPaso3(comentario, idUsuario);
		return resumen;			
	}
	
	
	@RequestMapping("paso2")
	public String paso2(String tarjeta, String numero, String titular, 
			HttpServletRequest request) {
		int idUsuario = 
				(int)request.getSession().getAttribute("usuario_id");
		
				servicioPedidos.procesarPaso2(tarjeta,numero,titular, idUsuario);
				String respuesta = "ok";
				return respuesta;			
	}
	
	@RequestMapping("paso1")
	public String paso1(String nombre, String direccion, String provincia, String codigoPostal, String email, String movil, HttpServletRequest request) {
		String respuesta = "";
		int idUsuario = 
				(int)request.getSession().getAttribute("usuario_id");
		servicioPedidos.procesarPaso1(nombre, direccion, provincia, codigoPostal, email, movil, idUsuario);
		respuesta = "ok";
		return respuesta;
	}

}

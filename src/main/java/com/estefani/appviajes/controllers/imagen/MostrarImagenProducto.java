package com.estefani.appviajes.controllers.imagen;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.estefani.appviajes.servicios.ServicioViajes;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class MostrarImagenProducto {

	@Autowired
	private ServicioViajes servicioViajes;
	
	//excepcionalmente este metodo no va a devolver un String indicando una vista
	//sino que programaremos manualmente que es lo que va a devolver al usuario
	@RequestMapping("mostrar_imagen")
	public void mostrarImagen(@RequestParam("id") Long id, HttpServletResponse response) throws IOException {
		byte[] info = servicioViajes.obtenerViajePorId(id).getImagenPortada();
		if(info == null) {
			return;
		}
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		response.getOutputStream().write(info);
		response.getOutputStream().close();				
	}
	
}




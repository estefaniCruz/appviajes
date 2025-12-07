package com.estefani.appviajes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.estefani.appviajes.setUp.SetUp;

@Controller
public class InicioController {
	@Autowired 
	private SetUp setUp;
	
	@Autowired
	private MessageSource messageSource;
	
	//cada uno de estos @RequestMapping, son servicios, que tiende este controlador
	//si yo en el parentesis de este requestMapping no pongo nada, atenderá la ruta por defecto de la aplicación
	@RequestMapping()
	public String inicio() {
		//yo quiero devolver con este servicio, tienda.html (carpeta template)
		setUp.prepararRegistros();
		
		String idiomaActual = 
				messageSource.getMessage("idioma", null, LocaleContextHolder.getLocale());
		
		System.out.println("idiomaActual: " + idiomaActual);
		return "tienda_" + idiomaActual;
	}

}

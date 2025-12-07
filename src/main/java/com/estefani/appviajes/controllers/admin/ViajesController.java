package com.estefani.appviajes.controllers.admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.estefani.appviajes.model.Categoria;
import com.estefani.appviajes.model.Viaje;
import com.estefani.appviajes.servicios.ServicioCategorias;
import com.estefani.appviajes.servicios.ServicioViajes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;


@Controller
@RequestMapping("admin/")
public class ViajesController {
	
	@Autowired
	private ServicioViajes servicioViajes;
	
	@Autowired
	private ServicioCategorias servicioCategorias;

	@RequestMapping("editarViaje")
	public String editarViaje(@RequestParam("id") Long id, Model model) {
	    Viaje viaje = servicioViajes.obtenerViajePorId(id);

	    // Solo intentamos sacar el ID si la categoría EXISTE realmente.
	    if (viaje.getCategoria() != null) {
	        
	        viaje.setIdCategoria(viaje.getCategoria().getId());
	    }
	    

	    model.addAttribute("viajeEditar", viaje);
	    model.addAttribute("categorias", servicioCategorias.obtenerCategorias());
	    
	    return "admin/viajes_editar";
	}
	
	
	
	@RequestMapping("guardarCambiosViaje")
	public String guardarCambiosViaje(@ModelAttribute("viajeEditar")@Valid Viaje viajeEditar, BindingResult resultadoValidaciones, Model model) {
		
		if(resultadoValidaciones.hasErrors()) {
			return "admin/viajes_editar"; //si has tenido erroes te remanda al formulario
		}
	
		servicioViajes.actualizarViaje(viajeEditar);
		return obtenerViajes(model);
	}
	
	
	@RequestMapping("registrarViaje")
	public String registrarViaje(Model model) {
		Viaje v = new Viaje();
		v.setPrecio(20);
		model.addAttribute("nuevoViaje", v);
		//vamos a meter las categorias en model para que le lleguen a la vista
		
		model.addAttribute("categorias", servicioCategorias.obtenerCategorias());
		
		return "admin/viajes_registro";
	}
	
	
	@RequestMapping("guardarViaje")
	public String guardarViaje(@ModelAttribute("nuevoViaje") @Valid Viaje nuevoViaje, BindingResult resultadoValidaciones, Model model ) {
		
		if(resultadoValidaciones.hasErrors()) {
			return "admin/viajes_registro"; //si has tenido erroes te remanda al formulario
		}
		
		servicioViajes.registrarViaje(nuevoViaje);
		return obtenerViajes(model);
	}
	
	@RequestMapping("obtenerViajes")
	public String obtenerViajes(Model model) {

		model.addAttribute("viajes", servicioViajes.obtenerViajes());
		return "admin/viajes";
	}

	@RequestMapping("borrarViaje")
	public String borrarViaje( @RequestParam("id") Long id, Model model) {
		servicioViajes.borrarViaje(id);
		return obtenerViajes(model);
		
	}
	

	
}

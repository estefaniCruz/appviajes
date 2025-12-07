package com.estefani.appviajes.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.estefani.appviajes.model.Usuario;
import com.estefani.appviajes.servicios.ServicioUsuarios;

import jakarta.validation.Valid;

@Controller
@RequestMapping("admin/")
public class UsuariosController {
	
	
	
	@Autowired
	private ServicioUsuarios servicioUsuarios;
	
	@RequestMapping("editarUsuario")
	public String editarUsuario( @RequestParam("id") int id, Model model) {
		Usuario usuario = servicioUsuarios.obtenerUsuarioPorId(id);
		//aqui cargo lo que mandare, en este caso solo un objeto usuario en particular
		model.addAttribute("usuarioEditar",usuario);
		return "admin/usuarios_editar";
	}
	
//	@RequestMapping("guardarCambiosUsuario")
//	public String guardarCambiosUsuario(@ModelAttribute("usuarioEditar")  Usuario usuarioEditar,  Model model) {
//		
//	    servicioUsuarios.actualizarUsuario(usuarioEditar);
//	    return obtenerUsuarios(model); // método que recargará el listado entero
//	}
	
	@RequestMapping("guardarCambiosUsuario")
	public String guardarCambiosUsuario(@ModelAttribute("usuarioEditar") @Valid Usuario usuarioEditar, 
	                                    BindingResult bindingResult, 
	                                    Model model) {
	    
	    // --- 1. VALIDACIÓN DE EMAIL AL EDITAR ---
	    // Buscamos en la BBDD a ver si alguien ya tiene ese email
	    Usuario usuarioExistente = servicioUsuarios.obtenerUsuarioPorEmail(usuarioEditar.getEmail());
	    
	    // Si encontramos a alguien con ese email...
	    if (usuarioExistente != null) {
	        // ...y ese alguien NO SOY YO (los IDs son diferentes)
	        if (usuarioExistente.getId() != usuarioEditar.getId()) {
	            // Entonces es un error: estoy intentando robar el email de otro
	            bindingResult.rejectValue("email", "error.email", "Este email ya pertenece a otro usuario");
	        }
	    }

	    // --- 2. VALIDACIONES DE FORMATO (@Pattern, @Size...) ---
	    // Aquí es donde se captura el error del "nombre incorrecto" para que no explote
	    if (bindingResult.hasErrors()) {
	        // Si hay errores, volvemos a la vista de editar para mostrar los mensajes en rojo
	        return "admin/usuarios_editar"; 
	    }
	    
	    // --- 3. SI TODO ESTÁ BIEN, GUARDAMOS ---
	    servicioUsuarios.actualizarUsuario(usuarioEditar);
	    
	    // Usamos redirect para recargar la lista limpia
	    return obtenerUsuarios(model); 
	}
	
	@RequestMapping("registrarUsuario")
	public String registrarUsuario(Model model) {
		Usuario u = new Usuario();
		model.addAttribute("nuevoUsuario", u); //este mismo nombre de objeto tiene que ser identico en el html para que lo asocie
		return "admin/usuarios_registro";
	}
	
	
	
	@RequestMapping("guardarUsuario")
	public String guardarUsuario(@ModelAttribute("nuevoUsuario") @Valid Usuario nuevoUsuario, BindingResult resultadoValidaciones, Model model ) {
		
		// 1. VALIDACIÓN DE EMAIL DUPLICADO
	    // Ahora sí funcionará porque has creado el método en el servicio
	    if (servicioUsuarios.existeEmail(nuevoUsuario.getEmail())) {
	        resultadoValidaciones.rejectValue("email", "error.email", "Este email ya está registrado en el sistema");
	    }
		
		
		if(resultadoValidaciones.hasErrors()) {
			return "admin/usuarios_registro"; //si has tenido errores te remanda al formulario
		}
		
		servicioUsuarios.registrarUsuario(nuevoUsuario);
		return obtenerUsuarios(model);
	}
	
	@RequestMapping("obtenerUsuarios")
	public String obtenerUsuarios(Model model) {
		model.addAttribute("usuarios", servicioUsuarios.obtenerUsuarios());
		return "admin/usuarios";
	}
	
	
	@RequestMapping("borrarUsuario")
	public String borrarUsuario( @RequestParam("id") int id, Model model) {
		servicioUsuarios.borrarUsuario(id);
		return obtenerUsuarios(model); //esto me pintara la lista entera de usuarios
		
	}
	
	

}

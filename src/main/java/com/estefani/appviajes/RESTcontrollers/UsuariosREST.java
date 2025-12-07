package com.estefani.appviajes.RESTcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estefani.appviajes.model.Usuario;
import com.estefani.appviajes.servicios.ServicioUsuarios;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("UsuariosREST/")
public class UsuariosREST {

	@Autowired
	private ServicioUsuarios servicioUsuarios;

	@RequestMapping("login")
	public String login(String email, String pass, HttpServletRequest request) {
		Usuario usuario = servicioUsuarios.obtenerUsuarioPorEmailYpass(email, pass);
		String respuesta = "";
		if (usuario != null) {
			//si se identifica bn le metemos en sesion, le tenemos pillado por su id,
			//si quiere comprar algo usaremos ese id
			request.getSession().setAttribute("usuario_id", usuario.getId());
			respuesta = "ok," + usuario.getNombre();
		} else {
			respuesta = "error,email o pass incorrectos";
		}
		return respuesta;
	}

	@RequestMapping("registrar")
	public String registrar(String nombre, String apellidos, String pass, String email, String telefono) {
		System.out.println("voy a  registrar: " + nombre + " " + apellidos + " " + pass + " " + email + " " + telefono);
		// 2 opciones:metodo que verifique si existe ese email o
		// Se crea antes en la interfaz sino da error
		if (servicioUsuarios.obtenerUsuarioPorEmail(email) != null) { //si da distinto a null sigifica que hay uno registrado con ese email
			return "email ya registrado";
		}

		servicioUsuarios.registrarUsuario(new Usuario(nombre, apellidos, pass, email, telefono));
		return "registro ok, ya puedes identificarte";
	}

}

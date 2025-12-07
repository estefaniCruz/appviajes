package com.estefani.appviajes.servicios;

import java.util.List;

import com.estefani.appviajes.model.Usuario;



//en java empresarial va a ver muchos conceptos confusos
//en este caso se llama servicio al interfaz
//que va a definir las operaciones posibles con usuarios
//casi igual que cuando hemos usado el paquete repository
//o el paquete daos


public interface ServicioUsuarios {
	
	void registrarUsuario(Usuario usuario);
	
	List<Usuario> obtenerUsuarios();
	
	void borrarUsuario(int id);
	
	Usuario obtenerUsuarioPorId(int id);
	
	Usuario obtenerUsuarioPorEmail(String email);

	void actualizarUsuario(Usuario usuarioEditar);

	Usuario obtenerUsuarioPorEmailYpass(String email, String pass);
	
	boolean existeEmail(String email);

}

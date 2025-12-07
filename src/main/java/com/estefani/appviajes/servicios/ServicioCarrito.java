package com.estefani.appviajes.servicios;

import java.util.List;
import java.util.Map;

public interface ServicioCarrito {
	
	void agregarViaje(int idUsuario, long idProducto, int cantidad); 

	List<Map<String, Object>> obtenerViajesCarrito(int idUsuario);

	void quitarViajeCarrito(int idUsuario, Long idViaje);
	
	//void quitarVCarrito(int idUsuario, Long idViaje);
}

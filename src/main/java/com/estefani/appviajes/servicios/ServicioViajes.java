package com.estefani.appviajes.servicios;

import java.util.List;
import java.util.Map;

import com.estefani.appviajes.model.Viaje;



public interface ServicioViajes {
	
	//La carta del bar:
	
	void registrarViaje(Viaje viaje);
	
	List<Viaje> obtenerViajes(); //solo se define la lista
	
	void borrarViaje(long id);
	
	void actualizarViaje(Viaje viaje);
	
	Viaje obtenerViajePorId(long long1);
	
	// para la parte pública, que son los elementos REST
	
	List< Map<String, Object> > obtenerViajesParaFormarJSON();
	
	List< Map<String, Object> > obtenerViajesFiltradosParaFormarJSON(String estacion);
	
	
	
	
	

}

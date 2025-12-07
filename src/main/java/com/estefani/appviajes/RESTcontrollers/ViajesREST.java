package com.estefani.appviajes.RESTcontrollers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estefani.appviajes.model.Viaje;
import com.estefani.appviajes.servicios.ServicioViajes;

@RestController
@RequestMapping("viajesREST/")

public class ViajesREST {
	
	@Autowired
	private ServicioViajes servicioViajes;
	
	
	@RequestMapping("obtener")
	public List<Map<String, Object>> obtenerViajes() {		
		return servicioViajes.obtenerViajesParaFormarJSON();

	}
	
	@RequestMapping("filtrarViajes")
	public List<Map<String, Object>> filtrarViajes(String estacion) {		
		return servicioViajes.obtenerViajesFiltradosParaFormarJSON(estacion);

	}
}

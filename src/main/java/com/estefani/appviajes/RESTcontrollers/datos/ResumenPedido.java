package com.estefani.appviajes.RESTcontrollers.datos;

import java.util.List;
import java.util.Map;

public class ResumenPedido {

	// productos que hay en el carrito

	private List<Map<String, Object>> viajes;

	// datos del paso1
	private String nombreCompleto;
	private String direccion;
	private String provincia;
	private String codigoPostal;
	private String email;
	private String movil;

	// datos del paso2
	private String titularTarjeta;
	private String numeroTarjeta;
	private String tipoTarjeta;
	
	// datos del paso 3:
	private String comentario;
	
	public List<Map<String, Object>> getViajes() {
		return viajes;
	}
	public void setViajes(List<Map<String, Object>> viajes) {
		this.viajes = viajes;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMovil() {
		return movil;
	}
	public void setMovil(String movil) {
		this.movil = movil;
	}
	public String getTitularTarjeta() {
		return titularTarjeta;
	}
	public void setTitularTarjeta(String titularTarjeta) {
		this.titularTarjeta = titularTarjeta;
	}
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}
	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}
	public String getTipoTarjeta() {
		return tipoTarjeta;
	}
	public void setTipoTarjeta(String tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	
	
	
}

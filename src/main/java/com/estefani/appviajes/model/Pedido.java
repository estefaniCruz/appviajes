package com.estefani.appviajes.model;



import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Pedido {
	
		private String estado;

		private String nombreCompleto;

		private String direccion;

		private String provincia;

		private String codigoPostal;

		private String email;

		private String movil;

		private String titularTarjeta;

		private String numeroTarjeta;
		

		private String tipoTarjeta;

		private String comentario;

		
		@OneToMany(mappedBy = "pedido", fetch = FetchType.EAGER)
		private List<ProductoPedido> productosPedido =
			new ArrayList<ProductoPedido>();
		
		
		@ManyToOne(targetEntity = Usuario.class, optional = false)
//		@JoinColumn(name = "usuario_id") 
		private Usuario usuario;
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;

		public String getEstado() {
			return estado;
		}

		public void setEstado(String estado) {
			this.estado = estado;
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

		public List<ProductoPedido> getProductosPedido() {
			return productosPedido;
		}

		public void setProductosPedido(List<ProductoPedido> productosPedido) {
			this.productosPedido = productosPedido;
		}

		public Usuario getUsuario() {
			return usuario;
		}

		public void setUsuario(Usuario usuario) {
			this.usuario = usuario;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		

		
		
		
		
		

}

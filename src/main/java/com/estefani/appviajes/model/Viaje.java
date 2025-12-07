package com.estefani.appviajes.model;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tabla_viajes")
public class Viaje {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Size(min = 3, max = 30, message = "época debe tener entre 3 y 30 caracteres")
	@NotEmpty(message = "debes insertar una época")
	@Pattern(regexp = "[A-Za-z áéíóúÁÉÍÓÚñÑ]+", message = "solo letras y espacios")
	private String epoca;
	
	@Size(min = 3, max = 50, message = "destino debe tener entre 3 y 50 caracteres")
	@NotEmpty(message = "debes insertar un destino")
	@Pattern(regexp = "[A-Za-z áéíóúÁÉÍÓÚñÑ]+", message = "solo letras y espacios")
	private String destino;
	
	
	@NotNull(message = "debes insertar el número de noches")
	@Min(value = 1, message = "el mínimo es de 1 noche")
	@Max(value = 30, message = "el máximo es de 30 noches")
	private int noches;
	
	@Size(min = 1, max = 30, message = "tipo de alojamiento debe tener entre 3 y 30 caracteres")
	@NotEmpty(message = "debes insertar un tipo de alojamiento")
	@Pattern(regexp = "[A-Za-z áéíóúÁÉÍÓÚñÑ]+", message = "solo letras y espacios")
	private String tipo_alojamiento;
	
	@Size(min = 5, max = 500, message = "descripción debe tener entre 10 y 100 caracteres")
	@NotEmpty(message = "debes insertar una descripción")
	private String descripcion;
	
	@NotNull(message = "debes insertar un precio")
	@Min(value = 20, message = "el precio mínimo es de 20 euro")
	@Max(value = 1000, message = "el precio maximo es 1000 euros")
	private double precio;
	
	@NotNull(message = "debes indicar si está activo o no")
	private boolean activo;

	@Lob
	@Column(name = "imagen_portada", columnDefinition = "LONGBLOB")
	private byte[] imagenPortada;

	@Transient
	private MultipartFile imagen;
	
	@Transient
	private long idCategoria;

	// muchos registros de la tabla carrito pueden estar asociados a un mismo libro
	// el mismo producto puede aparecer en varios carritos
	@OneToMany(mappedBy = "viaje")
	private List<Carrito> carritos = new ArrayList<Carrito>();
	
	@ManyToOne
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;

	

	public byte[] getImagenPortada() {
		return imagenPortada;
	}

	public void setImagenPortada(byte[] imagenPortada) {
		this.imagenPortada = imagenPortada;
	}

	public Viaje() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Viaje(long id, String epoca, String destino, int noches, String tipo_alojamiento, String descripcion,
			double precio, boolean activo) {
		super();
		this.id = id;
		this.epoca = epoca;
		this.destino = destino;
		this.noches = noches;
		this.tipo_alojamiento = tipo_alojamiento;
		this.descripcion = descripcion;
		this.precio = precio;
		this.activo = activo;
	}

	public Viaje(String epoca, String destino, int noches, String tipo_alojamiento, String descripcion, double precio,
			boolean activo) {
		super();
		this.epoca = epoca;
		this.destino = destino;
		this.noches = noches;
		this.tipo_alojamiento = tipo_alojamiento;
		this.descripcion = descripcion;
		this.precio = precio;
		this.activo = activo;
	}

	public MultipartFile getImagen() {
		return imagen;
	}

	public void setImagen(MultipartFile imagen) {
		this.imagen = imagen;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEpoca() {
		return epoca;
	}

	public void setEpoca(String epoca) {
		this.epoca = epoca;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public int getNoches() {
		return noches;
	}

	public void setNoches(int noches) {
		this.noches = noches;
	}

	public String getTipo_alojamiento() {
		return tipo_alojamiento;
	}

	public void setTipo_alojamiento(String tipo_alojamiento) {
		this.tipo_alojamiento = tipo_alojamiento;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	

	public List<Carrito> getCarritos() {
		return carritos;
	}

	public void setCarritos(List<Carrito> carritos) {
		this.carritos = carritos;
	}
	
	public long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "Viaje [id=" + id + ", epoca=" + epoca + ", destino=" + destino + ", noches=" + noches
				+ ", tipo_alojamiento=" + tipo_alojamiento + ", descripcion=" + descripcion + ", precio=" + precio
				+ ", activo=" + activo + "]";
	}

}

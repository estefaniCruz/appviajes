package com.estefani.appviajes.model;

import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tabla_usuarios")
public class Usuario {
	@Size(min = 2, max = 30, message = "nombre debe tener entre 2 y 30 caracteres")
	@NotEmpty(message = "debes insertar un nombre")
	@Pattern(regexp = "[A-Za-z áéíóúÁÉÍÓÚñÑ]+", message = "solo letras y espacios")
	private String nombre;
	
	@Size(min = 2, max = 50, message = "apellidos debe tener entre 2 y 50 caracteres")
	@NotEmpty(message = "debes insertar apellidos")
	@Pattern(regexp = "[A-Za-z áéíóúÁÉÍÓÚñÑ]+", message = "solo letras y espacios")
	private String apellidos;
	
	@Size(min = 3, max = 20, message = "contraseña debe tener entre 3 y 20 caracteres")
	@NotEmpty(message = "debes insertar una contraseña")
	private String pass;

	@NotEmpty(message = "debes insertar un email")
	@Email(message = "el formato del email no es válido") // Valida que tenga @ y punto
	// para que no se repita el email
	@Column(unique = true)
	private String email;

	
	@OneToMany(mappedBy = "usuario")
	private List<Carrito> viajesCarrito =
			new ArrayList<Carrito>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Size(min = 9, max = 15, message = "teléfono debe tener entre 9 y 15 caracteres")
	@NotEmpty(message = "debes insertar un teléfono")
	@Pattern(regexp = "[0-9+() -]+", message = "solo números y caracteres telefónicos permitidos")
	private String telefono;

	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Usuario(String nombre, String apellidos, String pass, String email, String telefono) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.pass = pass;
		this.email = email;
		this.telefono = telefono;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	
	public List<Carrito> getViajesCarrito() {
		return viajesCarrito;
	}

	public void setViajesCarrito(List<Carrito> viajesCarrito) {
		this.viajesCarrito = viajesCarrito;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}

package com.estefani.appviajes.servicios;

import java.util.List;

import com.estefani.appviajes.RESTcontrollers.datos.ResumenPedido;
import com.estefani.appviajes.model.Pedido;

public interface ServicioPedidos {
	//gestion de admnistracion:
	List<Pedido> obtenerPedidos();
	Pedido obtenPedidoPorId(int idPedido);
	void actualizarPedido(int idPedido, String estado);
	
	//operaciones ajax:
	void procesarPaso1(String nombre, String direccion, String provincia, String codigoPostal, String email, String movil, int idUsuario);

	void procesarPaso2(String tarjeta, String numero, String titular, int idUsuario);

	ResumenPedido procesarPaso3(String comentario, int idUsuario);
	
	void confirmarPedido(int idUsuario);
	
	

}

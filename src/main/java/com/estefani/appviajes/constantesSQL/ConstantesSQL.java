package com.estefani.appviajes.constantesSQL;

public class ConstantesSQL {
	
	public static final String SQL_OBTENER_VIAJES_PARA_JSON =
			//"select v.id, v.epoca, v.destino, v.noches, v.tipo_alojamiento, v.descripcion, v.precio, v.activo from tabla_viajes as v order by v.id desc";
			"select v.id, v.epoca, v.destino, v.noches, v.tipo_alojamiento, v.descripcion, v.precio, v.activo, c.nombre as nombre_categoria from tabla_viajes as v, categoria as c where v.categoria_id= c.id order by v.id desc";
			
//QUIERO: tener todos los atributos de carrito.html + los ids(usuario y viaje)
	public static final String SQL_OBTENER_VIAJES_CARRITO = 
			"SELECT "
			+ "C.USUARIO_ID , TV.EPOCA, TV.ID AS VIAJE_ID, TV.DESTINO, TV.NOCHES, TV.TIPO_ALOJAMIENTO, TV.DESCRIPCION, TV.PRECIO, TV.ACTIVO, C.CANTIDAD "
			+ "FROM CARRITO AS C, TABLA_VIAJES AS TV "
			+ "WHERE "
			+ "USUARIO_ID = :usuario_id AND TV.ID = C.VIAJE_ID ";
	
	public static final String SQL_ELIMINAR_VIAJE_CARRITO = 
			"DELETE FROM CARRITO WHERE VIAJE_ID = :viaje_id AND USUARIO_ID = :usuario_id";

	public static final String SQL_VACIAR_CARRITO = 
			"DELETE FROM CARRITO WHERE USUARIO_ID = :usuario_id";
	
	
	public static final String SQL_OBTENER_VIAJES_POR_EPOCAS_JSON =
			//"select v.id, v.epoca, v.destino, v.noches, v.tipo_alojamiento, v.descripcion, v.precio, v.activo from tabla_viajes as v order by v.id desc";
			"select v.id, v.epoca, v.destino, v.noches, v.tipo_alojamiento, v.descripcion, v.precio, v.activo, c.nombre as nombre_categoria from tabla_viajes as v, categoria as c where v.categoria_id = c.id and epoca = :epoca order by v.id desc";
			
}

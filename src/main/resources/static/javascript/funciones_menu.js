//funciones generales:
function checkout_paso_4(json){
	let html = Mustache.render(plantilla_checkout_4, json)
	$("#contenedor").html(html)
	//$("#boton_confirmar_pedido").click(function()
	$("#boton_confirmar_pedido").off("click").click(function(){
		$.post("pedidosREST/paso4").done(function(res){
			//alert("me voy a viajes")
			 alert(res)
			if(res == "ok"){
				obtenerViajes()
			}
		})
	})
}

function checkout_paso_3(){	
	$("#contenedor").html(plantilla_checkout_3)
	//$("#aceptar_paso_3").click(function()
	$("#aceptar_paso_3").off("click").click(function(){
		comentario = $("#comentario").val()
		
		
		$.post("pedidosREST/paso3",{
			comentario : comentario
			
		}).done(function(res){
		    //usar la plantilla de checkout paso 3 y procesarla con el json recibido
			//alert("mostrar plantilla checkout4")
			checkout_paso_4(res)
			
		})
	})
}


function checkout_paso_2(){	
	$("#contenedor").html(plantilla_checkout_2)
	//$("#aceptar_paso_2").click(function()
	$("#aceptar_paso_2").off("click").click(function(){
		tipo_tarjeta = $("#tipo_tarjeta").find(":selected").val()
		if(tipo_tarjeta == "0"){
			alert("selecciona un tipo de tarjeta")
			return
		}
		xxx = $("#numero_tarjeta").val()
		titular = $("#titular_tarjeta").val()
		
		// Coincide con @Pattern(regexp = "[A-Za-z áéíóúÁÉÍÓÚñÑ]+")
		const regexLetras = /^[a-zA-ZñÑáéíóúÁÉÍÓÚ\s]+$/;

		// Coincide con @Pattern(regexp = "[0-9]{13,19}")
		const regexTarjeta = /^[0-9]{13,19}$/

		// --- NÚMERO DE TARJETA (Solo 16 dígitos) ---
		    if (xxx == "" || xxx == null) {
		        $("#mensajeError").html("El número de tarjeta es obligatorio");
		        return false;
		    } else if (!regexTarjeta.test(xxx)) {
		        $("#mensajeError").html("El número debe tener 16 dígitos exactos (VISA/Mastercard)");
		        return false;
		    }

		// --- TITULAR DE LA TARJETA (@NotEmpty + @Pattern letras) ---
		if (titular == "" || titular == null) {
			$("#mensajeError").html("Debes insertar el titular de la tarjeta");
			return false;
		}else if(titular.length<4 || titular.length>80) {
			$("#mensajeError").html("el titular debe tener entre 4 y 80 caracteres");
						return false;
		}else if (!regexLetras.test(titular)) {
			$("#mensajeError").html("Solo letras y espacios en el titular");
			return false;
		} 
		
		
		$.post("pedidosREST/paso2",{
			tarjeta : tipo_tarjeta,
			numero: xxx, 
			titular: titular
		}).done(function(res){
		    //usar la plantilla de checkout paso 3 y procesarla con el json recibido
			if(res == "ok"){
						//alert("mostrar plantilla checkout3")
						checkout_paso_3()
					}
			
		})
	})
}

function checkout_paso_1(){
	xxx = $("#campo_nombre").val()
	direccion = $("#campo_direccion").val()
	provincia = $("#campo_provincia").val()
	codigoPostal = $("#campo_codigo_postal").val()
	email = $("#campo_email").val()
	movil = $("#campo_movil").val()
	
	
	//validaciones:
	const regexLetras = /^[a-zA-ZñÑáéíóúÁÉÍÓÚ\s]+$/; // Letras y espacios
	const regexEmail = /^[^\s@]+@[^\s@]+\.[^\s@]+$/; // Formato email
	const regexCP = /^[0-9]{5}$/;      // Exactamente 5 números
	const regexMovil = /^[0-9]{9}$/;   // Exactamente 9 números

	// 1. VALIDAR NOMBRE
	if (xxx == "" || xxx == null || xxx == undefined) {
		$("#mensajeError").html("El nombre es obligatorio");
		return false;
	} else if (xxx.length < 4 || xxx.length > 80) {
		$("#mensajeError").html("El nombre debe tener entre 4 y 80 caracteres");
		return false;
	} else if (!regexLetras.test(xxx)) {
		$("#mensajeError").html("El nombre solo admite letras y espacios");
		return false;
	}

	// 2. DIRECCIÓN (@Size min=5 max=150) ---
	if (direccion == "" || direccion == null) {
		$("#mensajeError").html("La dirección es obligatoria");
		return false;
	} else if (direccion.length < 5 || direccion.length > 150) {
		$("#mensajeError").html("La dirección debe tener entre 5 y 150 caracteres");
		return false;
	}

	// 3.PROVINCIA (@Size min=2 max=50) ---
	if (provincia == "" || provincia == null) {
		$("#mensajeError").html("La provincia es obligatoria");
		return false;
	} else if (provincia.length < 2 || provincia.length > 50) {
		$("#mensajeError").html("La provincia debe tener entre 2 y 50 caracteres");
		return false;
	} else if (!regexLetras.test(provincia)) {
		$("#mensajeError").html("La provincia solo admite letras y espacios");
		return false;
	}

	// 4.CÓDIGO POSTAL (Exactamente 5 números) ---
	if (codigoPostal == "" || codigoPostal == null) {
		$("#mensajeError").html("El código postal es obligatorio");
		return false;
	} else if (!regexCP.test(codigoPostal)) {
		$("#mensajeError").html("El código postal debe tener 5 números exactos");
		return false;
	}


	// 5. VALIDAR EMAIL (Formato)
	if (email == "" || email == null || email == undefined) {
		$("#mensajeError").html("El email es obligatorio");
		return false;
	} else if (!regexEmail.test(email)) {
		$("#mensajeError").html("El formato del email no es válido");
		return false;
	}



	// 6.MÓVIL (Exactamente 9 números) ---
	if (movil == "" || movil == null) {
		$("#mensajeError").html("El móvil es obligatorio");
		return false;
	} else if (!regexMovil.test(movil)) {
		$("#mensajeError").html("El móvil debe tener 9 números");
		return false;
	}
	//alert("mandar la info insertada a pedidosREST")
	$.post("pedidosREST/paso1",{
		nombre: xxx,
		direccion: direccion,
		provincia: provincia,
		codigoPostal: codigoPostal,
		email: email,
		movil: movil
	}).done(function(res){
		alert(res)
		if(res == "ok"){
//			alert("mostrar plantilla checkout2")
			//$("#contenedor").off(); // Elimina todos los eventos en el contenedor

			checkout_paso_2()
		}
	})			
}//end checkout_paso_1

function checkout_paso_0(){	
	$("#contenedor").html(plantilla_checkout_1)
	$("#aceptar_paso_1").off("click").click(checkout_paso_1)
	
}//end checkout_paso_0

function mostrarCarrito(){
	if(nombre_login == ""){
			alert("tienes que identificarte para comprar viajes")
	}else{
		//$("#contenedor").html(plantilla_carrito)
		$.get("carritoREST/obtener", function(r){
			if(r.length == 0){
				alert("aun no has agregado nada al carrito")
			}else{
				let html = Mustache.render(plantilla_carrito, r )
				$("#contenedor").html(html)	
				$("#realizar_pedido").click(checkout_paso_0)
				
				
				//decir que hay que hacer cuando se haga click 
				//en el enlace de borrar producto
				$(".enlace-borrar-viaje-carrito").click(function(){	
					if ( ! confirm("¿estas seguro?")){
						return
					}				
				 	let idViaje = $(this).attr("id-viaje")	
					alert("mandar el id de viaje: " + idViaje + " a carritoREST para que lo borre del carrito")
					$.post("carritoREST/eliminar",{
						id: idViaje
					}).done(function(res){
						alert(res)
						if(res == "ok"){
							mostrarCarrito()
						}
					})//end done
				})//end click
			}//end else							
		})//end get
	}//end else
}//end function


function comprar_viaje(){
	if(nombre_login == ""){
		alert("tienes que identificarte para comprar viajes")
	}else{
		var id_viaje = $(this).attr("id-viaje")
		alert("añadir viaje de id: " + id_viaje + " al carrito")	
		//invocar a una operacion de CarritoREST para agregar
		//el producto al carrito
		$.post("carritoREST/agregarViaje",{
			id: id_viaje,
			cantidad: 1
		}).done(function(res){
			alert(res)
		})
	}
}//end comprar_producto
	
function obtenerViajes(){
	//esta llamando a viajeREST al metodo obtener y siempre un REST dev un json
	//lo que le de el servicios al rest se guarda en var r
	$.get("viajesREST/obtener", function(r){
		//codigo a ejecutar cuando reciba la respuesta del recurso indicado
		//alert("recibido: " + r );
		var viajes = r // JSON.parse(r)
		console.log(viajes)
		//todo lo me hace el viajes mustache esa traduccion ya hecha se guarda aqui en info
		//solo lo tengo pero no se esta pintando
		var info = Mustache.render( 
				plantilla_viajes , { xxx : "hola desde mustache", array_viajes: viajes } ) 
		//buscame el contenido de este id y devuelvemelo
		//.html(info)-->lo k me acabas de dar del html cambiamelo por info
		$("#contenedor").html(info)
		//oye buscame estas clases y cuando click en esas clases hazme esta funcion
		$(".enlace_comprar_viaje").click(comprar_viaje)	
		$("#estacion-preferida").change(filtrar_viajes)
			
	})//end $.get
	$("#contenedor").html("cargando...");
}//end obtenerViajes



function filtrar_viajes() {
	var estacion = $(this).val()
	console.log(estacion)

	$.post("viajesREST/filtrarViajes", {
		estacion: estacion,

	}).done(function(r) {
		var viajes = r // JSON.parse(r)
		console.log(viajes)
		//todo lo me hace el viajes mustache esa traduccion ya hecha se guarda aqui en info
		//solo lo tengo pero no se esta pintando
		var info = Mustache.render(
			plantilla_viajes, { xxx: "hola desde mustache", array_viajes: viajes })
		//buscame el contenido de este id y devuelvemelo
		//.html(info)-->lo k me acabas de dar del html cambiamelo por info
		$("#contenedor").html(info)
		//oye buscame estas clases y cuando click en esas clases hazme esta funcion
		$(".enlace_comprar_viaje").click(comprar_viaje)
		$("#estacion-preferida").change(filtrar_viajes)
	})

}

function mostrarLogin(){
	$("#contenedor").html(plantilla_login)
	$("#form_login").submit(function(e){
		e.preventDefault()
		var email = $("#email").val()
		var pass = $("#pass").val()
		
		//validacion:
		// email
		const regexEmail = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;


		var email = $("#email").val();

		if (email == null || email == undefined || email == "") {
			$("#mensajeError").html("el email es obligatorio");
			return false;
		} else {
			// 2. Aquí está la condición que te falta
			// "Si el email NO cumple el test de la regex..."
			if (!regexEmail.test(email)) {
				$("#mensajeError").html("el formato del email no es válido");
				return false;
			}
		}

		if (pass == null || pass == undefined || pass == "") {
			$("#mensajeError").html("la contraseña es obligatoria")
			return false
		} else {
			if (pass.length < 3 || pass.length > 20) {
				$("#mensajeError").html("la contraseña debe tener entre 3 y 20 caracteres")
				return false
			}
		}
		
		
		$.post("UsuariosREST/login",{
			email: email,
			pass: pass
		}).done(function(res){
			var parte1 = res.split(",")[0]
			var parte2 = res.split(",")[1]
			if (parte1 == "ok"){
				alert("bienvenido " + parte2 + " ya puedes comprar")
				nombre_login = parte2
				$("#login_usuario").html("hola " + parte2)
			}else{
				alert(res)
			}
		})//end done		
	})//end submit
}//end mostrarLogin

function mostrarRegistro(){
	$("#contenedor").html(plantilla_registro)
	//vamos a interceptar el envio de formulario
	$("#form_registro").submit(function(e){
		e.preventDefault()
		//alert("se intenta enviar form")
		//recoger los datos del form y mandarselos a UsuariosREST
		var nombre = $("#nombre").val()
		var apellidos = $("#apellidos").val()
		var pass = $("#pass").val()
		var email = $("#email").val()
		var telefono = $("#telefono").val()
		
		//nombre:
		// 1. Definimos el patrón: Letras (mayús/minús), ñ, tildes y espacios (\s)
		const regexLetras = /^[a-zA-ZñÑáéíóúÁÉÍÓÚ\s]+$/;

		if (nombre == null || nombre == undefined || nombre == "") {
			$("#mensajeError").html("el nombre es obligatorio");
			return false;
		}
		else if (nombre.length < 2 || nombre.length > 30) {
			$("#mensajeError").html("nombre debe tener entre 2 y 30 caracteres");
			return false;
		}
		
		else if (!regexLetras.test(nombre)) {
			$("#mensajeError").html("el nombre solo admite letras y espacios");
			return false;
		}
		//apellidos:
		if (apellidos == null || apellidos == undefined || apellidos == "") {
					$("#mensajeError").html("el apellido es obligatorio");
					return false;
				}
				else if (apellidos.length < 2 || apellidos.length > 50) {
					$("#mensajeError").html("apellido debe tener entre 2 y 50 caracteres");
					return false;
				}
				
				else if (!regexLetras.test(apellidos)) {
					$("#mensajeError").html("el apellido solo admite letras y espacios");
					return false;
				}

		// email
		const regexEmail = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

		
		var email = $("#email").val();

		if (email == null || email == undefined || email == "") {
			$("#mensajeError").html("el email es obligatorio");
			return false;
		} else {
			// 2. Aquí está la condición que te falta
			// "Si el email NO cumple el test de la regex..."
			if (!regexEmail.test(email)) {
				$("#mensajeError").html("el formato del email no es válido");
				return false;
			}
		}
				
		if(pass==null || pass==undefined || pass==""){
						$("#mensajeError").html("la contraseña es obligatoria")
							return false
				} else{
					if(pass.length < 3 || pass.length > 20){
						$("#mensajeError").html("la contraseña debe tener entre 3 y 20 caracteres")
						return false
						}
				}
				
		
				
		const regexTelefono = /^[0-9+() -]+$/;


		var telefono = $("#telefono").val();

		// telefono
		if (telefono == null || telefono == undefined || telefono == "") {
			$("#mensajeError").html("debes insertar un teléfono");
			return false;
		} else {
			// Validar Tamaño (min 9, max 15)
			if (telefono.length < 9 || telefono.length > 15) {
				$("#mensajeError").html("teléfono debe tener entre 9 y 15 caracteres numéricos");
				return false;
			}

			// Validar Patrón (Regex)
			// Si NO cumple el test, entra al error
			if (!regexTelefono.test(telefono)) {
				$("#mensajeError").html("solo números y caracteres telefónicos permitidos");
				return false;
			}
		}
		
		
		$.post("UsuariosREST/registrar",{
			nombre: nombre, 
			apellidos: apellidos,
			pass: pass,
			email: email,
			telefono: telefono
			
		}).done(function(res){
			alert(res)
		})//end done
	})//end submit form
}//end mostrarRegistro


//cuando le haga click a ese enlace me ejecutas esta funcion
$("#enlace_viajes").click(obtenerViajes)
$("#enlace_identificarme").click(mostrarLogin)
$("#enlace_registrarme").click(mostrarRegistro)
$("#enlace_carrito").click(mostrarCarrito)

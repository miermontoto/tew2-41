document.getElementById("loadUsersButton").addEventListener("click", function() {
	$.ajax({
		url: "http://localhost:8080/Entrega1/redsocial.json", // Cambiar esta URL por la que corresponde de nuestro proyecto
		type: "GET",
		dataType: "json",

		success: function(redsocial) {
			let listaUsuarios = redsocial.usuarios;
			let contador = 0; // Contador para mirar los que llevamos subidos
			listaUsuarios.forEach(function(usuario) {
			  	let user = {
					email: usuario.email,
					password: usuario.passwd,
					role: 1,
					username: usuario.nombre
				};
			  	
			  	document.getElementById("usuarioActual").innerHTML = "Cargando al usuario " + usuario.nombre;
			  	contador = contador + 1; // Incremento el contador
			  	
				// Envío al servidor lo requerido
				UserServiceRs.add({
					$entity: user,
					$contentType: "application/json"
				});
			});
			
			if(contador === listaUsuarios.length){
				
			}
		} // Cierre de la función de éxito (success)
	}); // Cierre de $.ajax
}); // Cierre del método addEventListener

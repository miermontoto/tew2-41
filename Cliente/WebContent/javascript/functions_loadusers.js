document.getElementById("loadUsersButton").addEventListener("click", function() {
	$.ajax({
		url: "http://localhost:8080/Servidor/redsocial.json", // Cambiar esta URL por la que corresponde de nuestro proyecto
		type: "GET",
		dataType: "json",

		success: function(redsocial) {
			let listaUsuarios = redsocial.usuarios;

			listaUsuarios.forEach(function(usuario) {
			  	let user = {
					email: usuario.email,
					password: usuario.passwd,
					role: 1,
					username: usuario.nombre
				};


				// Envío al servidor lo requerido
				UserServiceRs.add({
					$entity: user,
					$contentType: "application/json"
				});
			});
		} // Cierre de la función de éxito (success)
	}); // Cierre de $.ajax
}); // Cierre del método addEventListener

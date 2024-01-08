document.getElementById("loadUsersButton").addEventListener("click", function() {
	$.ajax({
		url: "http://localhost:8080/Entrega1/redsocial.json", // Cambiar esta URL por la que corresponde de nuestro proyecto
		type: "GET",
		dataType: "json",
		
		success: function(redsocial) {
			tbRedSocial = localStorage.getItem("tbRedSocial"); 
			if (tbRedSocial == null) 
				tbRedSocial = [];
			
			console.log(redsocial);
			alert("Recibida respuesta con éxito!");
			
			var listaUsuarios = redsocial.usuarios;

			// Itero por la lista de usuarios.
			listaUsuarios.forEach(function(usuario) {
			  console.log(usuario.nombre);
			  
			  var usuario = {
						email: usuario.email,
						password: usuario.passwd,
						role: 1,
						username: usuario.nombre
					};
					
				
					// Envío al servidor lo requerido
					UserServiceRs.add({
						$entity: usuario,
						$contentType: "application/json"
					});
					
					
			});

			for (var i in redsocial) {
				
			/*	var usuario = JSON.stringify({
					email: redsocial[i].email,
					password: redsocial[i].passwd,
					role: redsocial[i].rol,
					username: redsocial[i].nombre
				});*/
				
				
			}
		} // Cierre de la función de éxito (success)
	
	}); // Cierre de $.ajax
	
}); // Cierre del método addEventListener

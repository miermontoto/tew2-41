document.getElementById("loadUsersButton").addEventListener("click", function() {
	$.ajax({
		url: "http://localhost:8080/Servidor/redsocial.json", // Cambiar esta URL por la que corresponde de nuestro proyecto
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
			  	
			  	contador = contador + 1; // Incremento el contador
			  	
				// Envío al servidor lo requerido
				UserServiceRs.add({
					$entity: user,
					$contentType: "application/json"
				});
			});
			
			if(contador === listaUsuarios.length){
				 // Se ha cargado con éxito
				showMessages("success"); // Llamo a la función que se encargará de mostrar que ha habido éxito, además de ocultar carga.
			} else {
				showMessages("error");
			}
		} // Cierre de la función de éxito (success)
	}); // Cierre de $.ajax
}); // Cierre del método addEventListener

function showMessages(operation){

	var opInfoSuccess = document.getElementById("opInfoSuccess"); // Muestro al usuario el anuncio
	var opInfoFailure = document.getElementById("opInfoFailure");
	if(operation === "success"){
		opInfoSuccess.style.display = "block";
		opInfoSuccess.innerHTML = "Operación realizada con éxito";
	} else {
		opInfoFailure.style.display = "block";
		opInfoFailure.innerHTML = "Ha ocurrido un problema";
	}
}

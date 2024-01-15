$("#loadUsersButton").on("click", function() {
	let token = sessionStorage.getItem("token");
	$.ajax({
		url: "http://localhost:8080/Servidor/redsocial.json", // Cambiar esta URL por la que corresponde de nuestro proyecto
		type: "GET",
		dataType: "json",

		success: function(redsocial) {
			let listaUsuarios = redsocial.usuarios;
			alert("Usuarios cargados");
			let contador = 0; // Contador para mirar los que llevamos subidos
			listaUsuarios.forEach(function(usuario) {
				let patchedRole = 1 // Por defecto el rol es 1 (usuario)
				if (usuario.rol === "admin") patchedRole = 0; // Si es admin, el rol es 0

			  	let user = {
					email: usuario.email,
					password: usuario.passwd,
					role: patchedRole,
					username: usuario.nombre,
					token: token
				};

			  	contador = contador + 1; // Incremento el contador

				// Envío al servidor lo requerido
				UserServiceRs.batchAdd({
					$entity: user,
					$contentType: "application/json"
				});
			});

			if (contador === listaUsuarios.length) {
				showMessages("success");
			} else {
				showMessages("error");
			}
		}, error: function() {
			// Ha ocurrido un error durante la solicitud AJAX
			showMessages("error");
		}
	}); // Cierre de $.ajax
}); // Cierre del método addEventListener

function showMessages(operation) {
    let opInfoSuccess = $("#opInfoSuccess");
    let opInfoFailure = $("#opInfoFailure");

	opInfoSuccess.css("display", "block");
    if (operation === "success") opInfoSuccess.html("Operación realizada con éxito");
    else opInfoFailure.html("Ha ocurrido un problema");
}

document.getElementById("loadUsersButton").addEventListener("click", function() {
	$.ajax({
		url: "http://localhost/Servidor/redsocial.json", // Cambiar esta URL por la que corresponde de nuestro proyecto
		type: "GET",
		dataType: "json",
		
		success: function(redsocial) {
			tbRedSocial = localStorage.getItem("tbRedSocial"); // Corregí el nombre del método getItem
			tbRedSocial = JSON.parse(tbRedSocial);
			if (tbRedSocial == null) 
				tbRedSocial = [];
			alert("Recibida respuesta con éxito!");

			for (var i in redsocial) {
				var usuario = JSON.stringify({
					email: redsocial[i].email,
					passwd: redsocial[i].passwd,
					role: redsocial[i].rol,
					username: redsocial[i].nombre
				});
				UserServiceRs.add({
					$entity: usuario,
					$contentType: "application/json"
				});
			}
		} // Cierre de la función de éxito (success)
	
	}); // Cierre de $.ajax
	
}); // Cierre del método addEventListener

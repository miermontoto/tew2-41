function Model() {
	
	// Función que se comunica con el servidor, enviándole el usuario que se ha creado.
	this.register = function(user) {
		return UserServiceRs.add({ // Llamo a la función de registro de LoginServiceRs
			$entity : user,
			$contentType : "application/json"});
	}
	
	 // Función para guardar el token en el almacenamiento local.
    this.setToken = function(token) {
        sessionStorage.setItem("token", token);
    }
    
	
};

function View() {
	
	// Cargo el usuario desde el formulario de registro.
	this.loadUserFromForm = function() {
		var user = {
				email : $("#inputEmail").val(),
				username : $("#inputUsername").val(),
				password : $("#inputPassword").val()
		};
		
		return user;
	}

};

function Controller(model, view) {
    // Inicialización del modelo y la vista.
    var userModel = model;
    var userView = view;

    this.init = function() {
        $("#registrationForm").bind("submit", function(event) {
            var user = userView.loadUserFromForm(); // Genero un usuario a la espera de enviarlo
            var token = userModel.register(user); // Enviamos el token y se obtiene como respuesta (o no) un user.

            if (token === "") { // El token es nulo (el usuario está mal)
            	$("#mensajeError").show(); // Muestra el mensaje de error que puse en el HTML.
            } else {
                userModel.setToken(token);
            }
            
        });
    }

   
}


// Se ejecuta al cargarse la página. Inicializa el modelo, vista, y finalmente, el controlador.
$(function() {
	var model = new Model();
	var view = new View();
	var control = new Controller(model, view);
	control.init();
});
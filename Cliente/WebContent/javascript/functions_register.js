function Model() {
	// Función que se comunica con el servidor, enviándole el usuario que se ha creado.
	this.register = function(user) {
		return UserServiceRs.add({ // Llamo a la función de registro de LoginServiceRs
			$entity : user,
			$contentType : "application/json"
		});
	}

	// Función para guardar el token en el almacenamiento local.
    this.setToken = function(token) {
        sessionStorage.setItem("token", token);
    }
};

function View() {
	// Cargo el usuario desde el formulario de registro.
	this.loadUserFromForm = function() {
		let user = {
			email: $("#inputEmail").val(),
			username: $("#inputUsername").val(),
			password: $("#inputPassword").val(),
			role: 1
		};

		return user;
	}
};

function Controller(model, view) {
    // Inicialización del modelo y la vista.
    let userModel = model;
    let userView = view;

    this.init = function() {
        $("#registrationForm").bind("submit", function(event) {
            let user = userView.loadUserFromForm(); // Genero un usuario a la espera de enviarlo
            let token = userModel.register(user); // Enviamos el token y se obtiene como respuesta (o no) un user.

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
	let model = new Model();
	let view = new View();
	let control = new Controller(model, view);
	control.init();
});

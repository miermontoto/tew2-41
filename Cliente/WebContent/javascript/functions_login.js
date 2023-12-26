function Model() {
	// Función que se comunica con el servidor, enviándole el usuario que se ha creado.
	this.login = function(user) {
		return LoginServiceRs.login({
			$entity : user,
			$contentType : "application/json"});
	}

	// Función para guardar el token en el almacenamiento local.
    this.setToken = function(token) {
        sessionStorage.setItem("token", token);
    }
}

function View() {
	this.loadUserFromForm = function() {
		var user = {
				email : $("#inputEmail").val(), // Establezco mismo email y username, para mantener compatibilidad con sistema de users.
				username: "",
				password : $("#inputPassword").val(),
				role: 0
		};
		return user;
	}

	// Cargo el usuario en el formulario.
	this.loadUserInForm = function(user) {
		$("#inputEmail").val(user.login);
		$("#inputPassword").val(user.password);
	}
};

function Controller(model, view) {
    // Inicialización del modelo y la vista.
    let userModel = model;
    let userView = view;

    this.init = function() {
        $("#loginForm").bind("submit", function(event) {
        	event.preventDefault(); // Prevengo el evento por defecto

            let user = userView.loadUserFromForm(); // Genero un usuario a la espera de enviarlo
            let token = userModel.login(user); // Enviamos el token y se obtiene como respuesta (o no) un user.
            console.log(token); // Lo muestro por consola

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

function Model() {
	// Función que se comunica con el servidor, enviándole el usuario que se ha creado.
	this.login = function(user) {
		return LoginServiceRs.login({
			$entity : user,
			$contentType : "application/json"
		});
	}

	// Función para guardar el token en el almacenamiento local.
    this.setToken = function(token) {
        sessionStorage.setItem("token", token);
    }

	this.getToken = function() {
		return sessionStorage.getItem("token");
	}
}

function View() {
	this.loadUserFromForm = function() {
		return {
			email: $("#inputEmail").val(),
			username: "tobeloggedin",
			password : $("#inputPassword").val(),
			role: 1 // Role.USER = 1
		};
	}

	this.clearMessages = function() {
		$("#mensajeError").hide();
		$("#mensajeExito").hide();
		$("#mensajeAlready").hide();
	}

	this.successMessage = function() {
		$("#mensajeExito").show();
	}

	this.errorMessage = function() {
		$("#mensajeError").show();
	}

	this.alreadyMessage = function() {
		$("#mensajeAlready").show();
	}

	this.enableButton = function() {
		$("#loginButton").prop("disabled", false);
	}

	this.disableButton = function() {
		$("#loginButton").prop("disabled", true);
	}
};

function Controller(model, view) {
    this.init = function() {
		this.buttonSubmit();
		this.checkToken();
    }

	this.checkToken = function() {
		if (model.getToken() !== null) view.alreadyMessage();
	}

	this.buttonSubmit = function() {
		$("#loginForm").bind("submit", function(event) {
        	event.preventDefault();

            let token = model.login(view.loadUserFromForm()); // Enviamos el token y se obtiene como respuesta (o no) un user.

			view.clearMessages();

            if (token === "") { // El token es nulo (el usuario está mal)
            	view.errorMessage();
				return;
            }

			view.successMessage();
			model.setToken(token);
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

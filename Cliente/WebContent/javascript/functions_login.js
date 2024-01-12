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

	this.myUser = function() {
		return LoginServiceRs.myUser({token : sessionStorage.getItem("token")});
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

	this.ackLogin = function(user) {
		window.parent.setUserData(user);
		window.location.replace("home.html");
	}
};

function Controller(model, view) {
    this.init = function() {
		if (model.getToken() !== null) view.ackLogin(model.myUser());

		$("#loginForm").bind("submit", function(event) {
        	event.preventDefault();

			let token;
			try {
            	token = model.login(view.loadUserFromForm()); // Enviamos el token y se obtiene como respuesta (o no) un user.
			} catch (error) {
				view.errorMessage();
				return;
			}

			view.clearMessages();

            if (token == null || token == "") {
            	view.errorMessage();
				return;
            }

			model.setToken(token);
			view.ackLogin(model.myUser());
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

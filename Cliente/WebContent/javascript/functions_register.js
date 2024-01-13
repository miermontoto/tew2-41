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

	this.myUser = function() {
		return LoginServiceRs.myUser({token : sessionStorage.getItem("token")});
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

	this.isRgpdChecked = function() {
		return $("#rgpdCheck").is(":checked");
	}

	this.hideErrors = function() {
		$("#mensajeError").hide();
		$("#rgpdError").hide();
	}

	this.rgpdError = function() {
		$("#rgpdError").show();
	}

	this.generalError = function() {
		$("#mensajeError").show();
	}

	this.ackLogin = function(user) {
		window.parent.setUserData(user);
		window.location.replace("home.html");
	}
};

function Controller(model, view) {
    this.init = function() {
        $("#registrationForm").bind("submit", function(event) {
			event.preventDefault();
			view.hideErrors();

			if (!view.isRgpdChecked()) {
				view.rgpdError();
				return;
			}

            let user = view.loadUserFromForm(); // Genero un usuario a la espera de enviarlo
			let token;
			try {
				token = model.register(user); // Envío el usuario al servidor para que lo registre
			} catch (error) {
				view.generalError();
				console.log(error);
				return;
			}

            if (model == null || token == "" || token == "error") {
				view.generalError();
				return;
			}

			model.setToken(token); // Guardo el token en el almacenamiento local
			view.ackLogin(model.myUser()); // Redirijo a la página de inicio
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

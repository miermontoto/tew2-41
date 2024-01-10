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
};

function Controller(model, view) {
    this.init = function() {
        $("#loginForm").bind("submit", function(event) {
        	event.preventDefault();

            let ucm = model.login(view.loadUserFromForm()); // Enviamos el token y se obtiene como respuesta (o no) un user.
			let user = ucm[0];
			let token = ucm[1];

			$("#mensajeError").hide();
			$("#mensajeExito").hide();

            if (token === "") { // El token es nulo (el usuario está mal)
            	$("#mensajeError").show();
				return;
            }

			model.setToken(token);
			$("#mensajeExito").show();
			$("#alumnosDropdown").text(user.username);
			$("#iframe").load("home.html");
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

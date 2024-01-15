function Model() {
	// Función que se comunica con el servidor, enviándole el usuario que se ha creado.
	this.reset = function() {
		return DatabaseServiceRs.reset({
			$entity : sessionStorage.getItem("token"),
			$contentType : "application/json"
		});
	}

	this.logout = function(token){
		return LoginServiceRs.logout({token : sessionStorage.getItem("token")});
	}

    this.getToken = function(){
    	sessionStorage.getItem("token"); // Obtengo el token para enviar al servidor
    }

	this.removeToken = function(){
		sessionStorage.removeItem("token"); // Función de borrado de elementos de sessionStorage
	}
};

function View() {

};

function Controller(model, view) {
    this.init = function() {
		if (!model.getToken()) {
			$('#iframe').attr('src', 'login.html');
		}

		$('#resetButton').on('click', function() {
			model.reset();
			window.location.href = "index.html";
		});

		$('#logoutButton').on('click', function() {
			model.logout();
			model.removeToken();
			window.location.href = "index.html";
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

this.setUserData = function(user) {
	$("#usernameTarget").text(user.username);
	$("#loginButton").hide();
	$("#registerButton").hide();
	$("#logoutButton").show();

	if (user.role == 0) $("#admin").show();
	$("#communities").show();
	$("#posts").show();
	$("#userTypeTarget").text(user.role == 0 ? "Rol: admin" : "Rol: usuario");
	$("#userTypeTarget").show();
	$("#sessionSpacer").show();
	$("#viewProfile").show();

	$("#viewProfile").on("click", function() {
		sessionStorage.setItem("user", user.email);
		$("#iframe").attr("src", "viewprofile.html");
	});
}

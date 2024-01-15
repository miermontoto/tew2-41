function Model() {
	this.getToken = function() {
		return sessionStorage.getItem("token");
	}
};

function View() {
	this.init = function() {
		$("#lastPosts").load("lastposts.html");
	}
};

function Controller(model, view) {
	this.init = function() {
		if (!model.getToken()) window.location.href = "login.html";
		view.init();
    }
}

// Se ejecuta al cargarse la página. Inicializa el modelo, vista, y finalmente, el controlador.
$(function() {
	let model = new Model();
	let view = new View();
	let control = new Controller(model, view);
	control.init();
});

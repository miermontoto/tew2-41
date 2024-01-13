function Model() {
	this.list = function(mixedData) {
		return PostServiceRs.getNewPosts({
			$entity: mixedData,
			$contentType : "application/json"
		});
	}

	this.getUser = function() {
		return LoginServiceRs.myUser({token : sessionStorage.getItem("token")});
	}
};

function View() {
	this.loadTable = function(data) {
		data.forEach(function(post) {
			let row = "<tr><td>" + post.content + "</td><td>" + post.communityName + "</td><td>" + post.userName + "</td><td>" + post.creationDate + "</td></tr>";
			$("#tableBody").append(row);
		});

		if (data.length == 0) {
			$("#tableBody").append("<tr><td colspan='4'>No hay posts recientes en tus comunidades.</td></tr>");
		}
	}
};

function Controller(model, view) {
    this.init = function() {
		let user = model.getUser();
		user.token = sessionStorage.getItem("token");

		view.loadTable(model.list(user));
    }
}

// Se ejecuta al cargarse la página. Inicializa el modelo, vista, y finalmente, el controlador.
$(function() {
	let model = new Model();
	let view = new View();
	let control = new Controller(model, view);
	control.init();
});

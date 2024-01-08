function Model() {
	this.list = function() {
		return MemberServiceRs.listJoined({
			user: localStorage.getItem("token")
		});
	}
};

function View() {
	this.loadTable = function(data) {
		data.forEach(function(community) {
			let row = "<tr><td>" + community.name + "</td><td>" + community.description + "</td></tr>";
			$("#tableBody").append(row);
		});
	}
};

function Controller(model, view) {
    this.init = function() {
		let list = model.list();
		console.log(list);
		view.loadTable(list);
    }
}

// Se ejecuta al cargarse la página. Inicializa el modelo, vista, y finalmente, el controlador.
$(function() {
	let model = new Model();
	let view = new View();
	let control = new Controller(model, view);
	control.init();
});

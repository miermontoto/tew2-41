function Model() {
	this.list = function() {
		salida = sessionStorage.getItem("token")
		return MemberServiceRs.listJoined({token : sessionStorage.getItem("token")});
	}
};

function View() {
	this.loadTable = function(data) {
		data.forEach(function(community) {
			let row = "<tr><td>" + community.name + "</td><td>" + community.description + "</td><td><button type='button' class='btn btn-primary' onclick='window.location.href=\"community.html?id=" + community.id + "\"'>Ver</button><button type='button' class='btn btn-danger' onclick='leaveCommunity(" + community.id + ")'>Dejar</button></td></tr>";
			$("#tableBody").append(row);
		});
	}
};

function Controller(model, view) {
    this.init = function() {
		view.loadTable(model.list());
    }
}

// Se ejecuta al cargarse la página. Inicializa el modelo, vista, y finalmente, el controlador.
$(function() {
	let model = new Model();
	let view = new View();
	let control = new Controller(model, view);
	control.init();
});

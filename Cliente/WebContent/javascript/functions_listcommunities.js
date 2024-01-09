function Model() {
	this.listAll = function() {
		return CommunityServiceRs.listAll();
	}

	this.listJoined = function() {
		return MemberServiceRs.listJoined({
			token: localStorage.getItem("token")
		});
	}
};

function View() {
	this.loadTable = function(all, joined) {
		all.forEach(function(community) {
			let row = "<tr><td>" + community.name + "</td><td>" + community.description + "</td></tr>";
			if (joined.includes(community.name)) {
				row += "<td><button type='button' class='btn btn-danger'>Dejar</button></td></tr>";
			} else {
				row += "<td><button type='button' class='btn btn-success'>Unirse</button></td></tr>";
			}
			$("#tableBody").append(row);
		});
	}
};

function Controller(model, view) {
    this.init = function() {
		view.loadTable(model.listAll(), model.listJoined());
    }
}

// Se ejecuta al cargarse la página. Inicializa el modelo, vista, y finalmente, el controlador.
$(function() {
	let model = new Model();
	let view = new View();
	let control = new Controller(model, view);
	control.init();
});

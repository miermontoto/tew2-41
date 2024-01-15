function Model() {
	this.listAll = function() {
		return CommunityServiceRs.listAll();
	}

	this.listJoined = function() {
		return MemberServiceRs.listMyJoined({token : sessionStorage.getItem("token")});
	}

	this.join = function(mixedData) {
		return MemberServiceRs.join({
			$entity: mixedData,
			$contentType : "application/json"
		});
	}

	this.getToken = function() {
		return sessionStorage.getItem("token");
	}
};

function View() {
	this.loadTable = function(all, joined) {
		$("#tableBody").empty();
		all.forEach(function(community) {
			let row = "<tr><td>" + community.name + "</td><td>" + community.description + "</td>";
			if (joined.filter(function(j) { return j.name === community.name; }).length === 0) {
				row += "<td><button type='button' class='btn btn-success'>Unirse</button></td></tr>";
			} else {
				row += "<td></td></tr>";
			}
			$("#tableBody").append(row);
		});
	}

	this.loadError = function() {
		$("#tableBody").empty();
		$("#tableBody").append("<tr><td colspan='3'>No se han podido cargar las comunidades.</td></tr>");
	}
};

function Controller(model, view) {
    this.init = function() {
		$("#searchInput").on("keyup", function() {
			let value = $(this).val().toLowerCase();
			$("#tableBody tr").filter(function() {
				$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
			});
		})

		let all = model.listAll();
		let joined = model.listJoined();
		if (!all || !joined) view.loadError();
		else view.loadTable(all, joined);

		let token = model.getToken();
		$("#tableBody").find("button").each(function() {
			$(this).click(function() {
				let target = $(this).parent().parent();
				let name = target.find("td:nth-child(1)").text();
				let description = target.find("td:nth-child(2)").text();

				let result = model.join({
					name: name,
					description: description,
					token: token
				});

				if (result === "success") {
					$(this).parent().html("<span class='badge badge-success'>Unido</span>");
				}
			});
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

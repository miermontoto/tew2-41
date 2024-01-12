function Model() {
	this.list = function() {
		return MemberServiceRs.listJoined({token : sessionStorage.getItem("token")});
	}

	this.leave = function(mixedData) {
		return MemberServiceRs.leave({
			$entity: mixedData,
			$contentType : "application/json"
		});
	}

	this.getToken = function() {
		return sessionStorage.getItem("token");
	}
};

function View() {
	this.loadTable = function(data) {
		data.forEach(function(community) {
			let row = "<tr><td>" + community.name + "</td><td>" + community.description +
				"</td><td>" +
				"<button type='button' class='btn btn-primary watch'><i class='bi bi-eye'></i> Ver</button> " +
				"<button type='button' class='btn btn-danger leave'><i class='bi bi-door-open-fill'></i> Dejar</button> " +
				"<button type='button' class='btn btn-success post'><i class='bi bi-file-earmark-text-fill'></i> Publicar</button>" +
				"</td></tr>";
			$("#tableBody").append(row);
		});
	}
};

function Controller(model, view) {
    this.init = function() {
		view.loadTable(model.list());

		let token = model.getToken();
		$("#tableBody").find("button.leave").each(function() {
			$(this).click(function() {
				let target = $(this).parent().parent()
				let name = target.find("td:nth-child(1)").text()
				let description = target.find("td:nth-child(2)").text()

				let result = model.leave({
					name: name,
					description: description,
					token: token
				});

				if (result === "success") {
					$(this).parent().html("<span class='badge badge-danger'>Abandonado</span>");
				}
			})
		})
    }
}

// Se ejecuta al cargarse la página. Inicializa el modelo, vista, y finalmente, el controlador.
$(function() {
	let model = new Model();
	let view = new View();
	let control = new Controller(model, view);
	control.init();
});

function Model() {
	this.list = function() {
		return MemberServiceRs.listMyJoined({token : sessionStorage.getItem("token")});
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
		$("#tableBody").empty();
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

	this.loadError = function() {
		$("#tableBody").empty();
		$("#tableBody").append("<tr><td colspan='3'>No se han podido cargar las comunidades.</td></tr>");
	}
};

function Controller(model, view) {
    this.init = function() {
		let data = model.list();
		if (data) view.loadTable(data);
		else view.loadError();

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
					$(this).parent().html("<span class='badge badge-primary'>Abandonado</span>");
				} else {
					$(this).parent().html("<span class='badge badge-danger'>Error</span>");
				}
			})
		})

		$("#tableBody").find("button.watch").each(function() {
			$(this).click(function() {
				let target = $(this).parent().parent()
				let name = target.find("td:nth-child(1)").text()

				sessionStorage.setItem("community", name)
				window.location.href = "viewcommunity.html"
			})
		})

		$("#tableBody").find("button.post").each(function() {
			$(this).click(function() {
				let target = $(this).parent().parent()
				let name = target.find("td:nth-child(1)").text()

				sessionStorage.setItem("community", name)
				window.location.href = "createpost.html"
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

function Model() {
	this.list = function(mixedData) {
		return PostServiceRs.getPostsByUser({
			$entity: mixedData,
			$contentType : "application/json"
		});
	}

	this.getUser = function() {
		return LoginServiceRs.myUser({token : sessionStorage.getItem("token")});
	}

	this.remove = function(mixedData) {
		return PostServiceRs.remove({
			$entity: mixedData,
			$contentType : "application/json"
		});
	}
};

function View() {
	this.loadTable = function(data) {
		data.forEach(function(post) {
			let row = "<tr><td>" + post.communityName + "</td><td>" + post.creationDate + "</td><td>" + post.content +
				"</td><td>" +
				"<button type='button' class='btn btn-danger erase'><i class='bi bi-eraser-fill'></i> Eliminar</button> " +
				"</td></tr>";
			$("#tableBody").append(row);
		});

		if (data.length == 0) {
			$("#tableBody").append("<tr><td colspan='4'>No has realizado ninguna publicación todavía.</td></tr>");
		}
	}
};

function Controller(model, view) {
    this.init = function() {
		let user = model.getUser();
		user.token = sessionStorage.getItem("token");

		view.loadTable(model.list(user));

		$("#tableBody").find("button.erase").each(function() {
			$(this).click(function() {
				let target = $(this).parent().parent()
				let community = target.find("td:nth-child(1)").text()
				let date = target.find("td:nth-child(2)").text()
				let content = target.find("td:nth-child(3)").text()

				let result = model.remove({
					communityName: community,
					creationDate: date,
					content: content,
					userEmail: user.email,
					userName: user.name,
					token: user.token
				});

				if (result === "success") {
					$(this).parent().html("<span class='badge badge-danger'>Eliminado</span>");
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

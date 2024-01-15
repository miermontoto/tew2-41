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
		$("#tableBody").empty();
		data.forEach(function(post) {
			let row = "<tr><td>" + post.content + "</td><td>" +
				"<a href='#' class='gotoCommunity'>" + post.communityName + "</a></td><td>" +
				post.userName + "</td><td>" + post.creationDate + "</td></tr>";
			$("#tableBody").append(row);
		});

		if (data.length == 0) {
			$("#tableBody").append("<tr><td colspan='4'>No hay posts recientes en tus comunidades. " +
				"<button id='btnDiscover' type='button' class='btn btn-primary'>Descubre comunidades</button>" +
				"</td></tr>");
			$("#btnDiscover").click(function() {
				window.location.href = "listcommunities.html";
			});
		}
	}

	this.loadError = function() {
		$("#tableBody").empty();
		$("#tableBody").append("<tr><td colspan='4'>No se han podido cargar los posts recientes.</td></tr>");
	}
};

function Controller(model, view) {
    this.init = function() {
		let user = model.getUser();
		user.token = sessionStorage.getItem("token");

		let data = model.list(user);
		if (data) view.loadTable(data);
		else view.loadError();

		$("#tableBody").find("a.gotoCommunity").each(function() {
			$(this).click(function() {
				let community = $(this).text();
				sessionStorage.setItem("community", community);
				window.location.href = "viewcommunity.html";
			})
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

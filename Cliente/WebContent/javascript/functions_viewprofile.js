function Model() {
	this.list = function(mixedData) {
		return PostServiceRs.getPostsByUser({
			$entity: mixedData,
			$contentType : "application/json"
		});
	}

	this.listJoined = function(mixedData) {
		return MemberServiceRs.listJoined({
			$entity: mixedData,
			$contentType : "application/json"
		});
	}

	this.getUser = function(email) {
		return LoginServiceRs.getUserByMail({email : email});
	}

	this.myUser = function() {
		return LoginServiceRs.myUser({token : sessionStorage.getItem("token")});
	}

	this.consumeUser = function() {
		let user = sessionStorage.getItem("user");
		sessionStorage.removeItem("user");
		return user;
	}

	this.getToken = function() {
		return sessionStorage.getItem("token");
	}
};

function View() {
	this.loadProfile = function(data) {
		$("#tableProfile").empty();
		$("#tableProfile").append("<tr><td>Nombre de usuario</td><td>" + data.username + "</td></tr>");
		$("#tableProfile").append("<tr><td>Correo electrónico</td><td>" + data.email + "</td></tr>");
		$("#tableProfile").append("<tr><td>Rol</td><td>" + data.role + "</td></tr>");
		$("#tableProfile").append("<tr><td>Comunidades a las que pertenece</td><td>" + data.communities + "</td></tr>");
		$("#tableProfile").append("<tr><td>Número de publicaciones</td><td>" + data.numPosts + "</td></tr>");
	}

	this.loadPosts = function(data) {
		$("#tablePosts").empty();
		data.forEach(function(post) {
			let row = "<tr><td>" + post.communityName + "</td><td>" + post.creationDate + "</td><td>" + post.content +
				"</td></tr>";
			$("#tablePosts").append(row);
		});
	}

	this.loadError = function() {
		$("#tablePosts").empty();
		$("#tablePosts").append("<tr><td colspan='4'>No se han podido cargar tus publicaciones.</td></tr>");

		$("#tableProfile").empty();
		$("#tableProfile").append("<tr><td colspan='4'>No se han podido cargar tus datos.</td></tr>");
	}
};

function Controller(model, view) {
	this.init = function() {
		let user = model.consumeUser();
		if (!user) {
			user = model.myUser();
		} else {
			user = model.getUser(user);
		}

		user.token = model.getToken();

		let posts = model.list(user);
		if (posts) view.loadPosts(posts);
		else view.loadError();

		let communities = model.listJoined(user);
		if (communities) {
			let names = [];
			communities.forEach(function(community) {
				names.push(community.name);
			});
			user.communities = names.join(", ");
		}
		user.numPosts = posts.length;
		user.role = user.role == "0" ? "Administrador" : "Usuario";

		view.loadProfile(user);
    }
}

// Se ejecuta al cargarse la página. Inicializa el modelo, vista, y finalmente, el controlador.
$(function() {
	let model = new Model();
	let view = new View();
	let control = new Controller(model, view);
	control.init();
});

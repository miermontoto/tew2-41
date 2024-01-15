function Model() {
	this.getJoined = function() {
		return MemberServiceRs.listMyJoined({token: sessionStorage.getItem('token')});
	}

	this.getPostsInCommunity = function(community) {
		return PostServiceRs.getPostsInCommunity({
			$entity: community,
			$contentType: 'application/json',
		});
	}

	this.getToken = function() {
		return sessionStorage.getItem('token');
	}

	this.consumeCommunity = function() {
		let community = sessionStorage.getItem('community');
		sessionStorage.removeItem('community');
		return community;
	}
};

function View() {
	this.initDropdown = function(data) {
		$('#communities').empty();
		if (data.length != 0) $('#communities').append('<h6 class="dropdown-header">Tus comunidades</h6>');
		data.forEach(function(com) {
			$('#communities').append('<a class="dropdown-item" href="#">' + com.name + '</a>');
		});
	}

	this.loadTable = function(posts) {
		$('#tableBody').empty();
		posts.forEach(function(post) {
			$('#tableBody').append('<tr><td>' + post.content + '</td><td>' + "<a href='#' class='gotoProfile' email='" + post.userEmail + "'>" + post.userName + "</a></td><td>" + post.creationDate + '</td></tr>');
		});

		$('#dropdownMenuButtonCommunity').removeClass('btn-warning');
		if (posts.length == 0) {
			$('#tableBody').append('<tr><td colspan="3">No hay posts en esta comunidad</td></tr>');
			$('#dropdownMenuButtonCommunity').addClass('btn-warning');
		}

		$("#tableBody").find("a.gotoProfile").each(function() {
			$(this).click(function() {
				let email = $(this).attr("email");
				sessionStorage.setItem("user", email);
				window.location.href = "viewprofile.html";
			});
		});
	}

	this.loadError = function() {
		$('#tableBody').empty();
		$('#tableBody').append('<tr><td colspan="3">No se han podido cargar los posts.</td></tr>');
	}

	this.setDropdown = function(name) {
		$('#dropdownMenuButtonCommunity').text(name);
		$('#dropdownMenuButtonCommunity').addClass('btn-success');
		$('#postButton').removeClass('disabled');
	}
};

function Controller(model, view) {
	this.init = function() {
		// actualizar el dropdown con las joined communities
		view.initDropdown(model.getJoined());

		// añadir listeners a los elementos del dropdown
		$('#communities').on('click', '.dropdown-item', function() {
			let name = $(this).text();

			let data = model.getPostsInCommunity({
				name: name,
				description: 'undefined',
				token: model.getToken()
			});

			if (data) view.loadTable(data);
			else view.loadError();

			view.setDropdown(name);
		});

		// consumir la comunidad guardada si existe
		let community = model.consumeCommunity();
		if (community) {
			let data = model.getPostsInCommunity({
				name: community,
				description: 'undefined',
				token: model.getToken()
			});

			if (data) view.loadTable(data);
			else view.loadError();

			view.setDropdown(community);
		}

		$('#postButton').click(function() {
			if ($(this).hasClass('disabled')) return;

			let community = $('#dropdownMenuButtonCommunity').text();
			sessionStorage.setItem('community', community);
			window.location.href = 'createpost.html';
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

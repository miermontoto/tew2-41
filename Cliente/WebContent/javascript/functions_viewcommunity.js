function Model() {
	this.getJoined = function() {
		return MemberServiceRs.listJoined({token: sessionStorage.getItem('token')});
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
			$('#tableBody').append('<tr><td>' + post.content + '</td><td>' + post.userName + '</td><td>' + post.creationDate + '</td></tr>');
		});

		$('#dropdownMenuButtonCommunity').removeClass('btn-warning');
		if (posts.length == 0) {
			$('#tableBody').append('<tr><td colspan="3">No hay posts en esta comunidad</td></tr>');
			$('#dropdownMenuButtonCommunity').addClass('btn-warning');
		}
	}

	this.setDropdown = function(name) {
		$('#dropdownMenuButtonCommunity').text(name);
		$('#dropdownMenuButtonCommunity').addClass('btn-success');
	}
};

function Controller(model, view) {
	this.init = function() {
		// actualizar el dropdown con las joined communities
		view.initDropdown(model.getJoined());

		// añadir listeners a los elementos del dropdown
		$('#communities').on('click', '.dropdown-item', function() {
			let name = $(this).text();

			view.loadTable(model.getPostsInCommunity({
				name: name,
				description: 'unknown description',
				token: model.getToken()
			}));

			view.setDropdown(name);
		});

		// consumir la comunidad guardada si existe
		let community = model.consumeCommunity();
		if (community) {
			view.loadTable(model.getPostsInCommunity({
				name: community,
				description: 'unknown description',
				token: model.getToken()
			}));

			view.setDropdown(community);
		}
    }
}

// Se ejecuta al cargarse la página. Inicializa el modelo, vista, y finalmente, el controlador.
$(function() {
	let model = new Model();
	let view = new View();
	let control = new Controller(model, view);
	control.init();
});

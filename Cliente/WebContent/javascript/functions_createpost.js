function Model() {
	this.getJoined = function() {
		return MemberServiceRs.listMyJoined({token: sessionStorage.getItem('token')});
	}

	this.getToken = function() {
		return sessionStorage.getItem('token');
	}

	this.consumeCommunity = function() {
		let community = sessionStorage.getItem('community');
		sessionStorage.removeItem('community');
		return community;
	}

	this.post = function(mixedData) {
		return PostServiceRs.add({
			$entity: mixedData,
			$contentType: 'application/json',
		});
	}

	this.getUser = function() {
		return LoginServiceRs.myUser({token : sessionStorage.getItem("token")});
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

	this.setDropdown = function(name) {
		$('#dropdownMenuButtonCommunity').text(name);
		$('#dropdownMenuButtonCommunity').addClass('btn-success');
		$('#postButton').removeClass('disabled');
	}

	this.hideMessages = function() {
		$('#error').hide();
		$('#success').hide();
	}

	this.showError = function() {
		$('#error').show();
	}

	this.showSuccess = function() {
		$('#success').show();
	}
};

function Controller(model, view) {
	this.init = function() {
		// actualizar el dropdown con las joined communities
		view.hideMessages();
		view.initDropdown(model.getJoined());

		// añadir listeners a los elementos del dropdown
		$('#communities').on('click', '.dropdown-item', function() {
			let name = $(this).text();
			view.setDropdown(name);
		});

		// consumir la comunidad guardada si existe
		let community = model.consumeCommunity();
		if (community) view.setDropdown(community);

		// añadir listener al botón de post
		$('#postButton').click(function() {
			let name = $('#dropdownMenuButtonCommunity').text();
			let content = $('#postContent').val();
			let token = model.getToken();
			let user = model.getUser();

			let result = model.post({
				communityName: name,
				userEmail: user.email,
				userName: user.name,
				token: token,
				content: content,
				creationDate: 'undefined'
			});

			if (result != "success") {
				view.showError();
				return;
			}

			view.showSuccess();
			$('#postContent').val('');
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

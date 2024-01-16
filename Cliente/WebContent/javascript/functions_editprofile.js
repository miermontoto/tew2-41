function Model() {
	this.myUser = function() {
		return LoginServiceRs.myUser({token : sessionStorage.getItem("token")});
	}

	this.getToken = function() {
		return sessionStorage.getItem("token");
	}

	this.updateUser = function(mixedData) {
		return UserServiceRs.update(mixedData);
	}
};

function View() {
	this.loadUser = function(data) {
		$("#username").val(data.name);
		$("#email").val(data.email);
	}

	this.loadFromForm = function() {
		return {
			name: $("#username").val(),
			password: $("#password").val(),
		}
	}

	this.checkSamePasswd = function() {
		return $("#password").val() === $("#password2").val();
	}

	this.hideMessages = function() {
		$("#errorNull").hide();
		$("#errorPasswd").hide();
		$("#success").hide();
	}

	this.showError = function() {
		$("#errorNull").show();
	}

	this.showPasswdError = function() {
		$("#errorPasswd").show();
	}

	this.showSuccess = function() {
		$("#success").show();
	}
};

function Controller(model, view) {
	this.init = function() {
		let user = model.myUser();
		if (!user) view.showError();

		view.loadUser(user);

		view.hideErrors();
		$("#submit").click(function() {
			if (!view.checkSamePasswd()) {
				view.showPasswdError();
				return;
			}

			let data = view.loadFromForm();
			data.email = user.email;
			data.role = user.role;
			data.token = model.getToken();

			let result = model.updateUser(data);

			if (result !== "success") {
				view.showError();
				return;
			}

			view.showSuccess();
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

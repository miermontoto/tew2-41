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
		$("#username").val(data.username);
		$("#email").val(data.email);
	}

	this.loadFromForm = function() {
		return {
			username: $("#username").val(),
			password: $("#password0").val(),
			newPassword: $("#password1").val()
		}
	}

	this.checkSamePasswd = function() {
		return $("#password1").val() === $("#password2").val();
	}

	this.hideMessages = function() {
		$("#errorNull").hide();
		$("#errorPasswd").hide();
		$("#oldPassError").hide();
		$("#unauthorized").hide();
		$("emptyuser").hide();
		$("#success").hide();
	}

	this.showError = function() {
		$("#errorNull").show();
	}

	this.showPasswdError = function() {
		$("#errorPasswd").show();
	}

	this.showOldPassError = function(){
		$("#oldPassError").show();
	}

	this.samePasswordsError = function() {
		$("#samePasswords").show();
	}

	this.showUnauthorizedError = function(){
		$("#unauthorized").show();
	}

	this.showEmptyUserError = function () {
		$("emptyuser").show();
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

		view.hideMessages();
		$("#submit").click(function(event) {
			event.preventDefault();
			if (!view.checkSamePasswd()) {
				view.showPasswdError();
				return;
			}

			let data = view.loadFromForm();
			data.email = user.email;
			data.role = user.role;
			data.token = model.getToken();

			let result = model.updateUser(data);

			switch(result){
				case "success":
					view.showSuccess();
					return;
				case "samePasswords":
					view.showSamePasswordsError();
					return;
				case "invalidOldPasswd":
					view.showOldPassError();
					return;
				case "unauthorized":
					view.showUnauthorizedError();
					return;
				case "emptyUser":
					view.showEmptyUserError();
					return;
				default:
					view.showError();
			}
		}
    )}
}

// Se ejecuta al cargarse la página. Inicializa el modelo, vista, y finalmente, el controlador.
$(function() {
	let model = new Model();
	let view = new View();
	let control = new Controller(model, view);
	control.init();
});

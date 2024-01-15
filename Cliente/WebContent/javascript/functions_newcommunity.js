function Model() {
	// Función que se comunica con el servidor, enviándole el usuario que se ha creado.
	this.createCommunity = function(mixedData) {
		return CommunityServiceRs.create({
			$entity : mixedData,
			$contentType : "application/json"
		});
	}

	this.getToken = function() {
		return sessionStorage.getItem("token");
	}

	this.join = function(mixedData) {
		return MemberServiceRs.join({
			$entity: mixedData,
			$contentType : "application/json"
		});
	}
};

function View() {
	this.loadFormData = function() {
		let formData = {
			name : $("#inputCommunityName").val(), // Establezco mismo email y username, para mantener compatibilidad con sistema de users.
			description : $("#inputCommunityDescription").val()
		};
		return formData;
	}

	this.hideErrors = function() {
		$("#success").hide();
		$("#error").hide();
		$("#errorNameRepeated").hide();
		$("#errorNameSpaces").hide();
		$("#warning").hide();
	}

	this.showSuccess = function() {
		$("#success").show();
	}

	this.showError = function() {
		$("#error").show();
	}

	this.showErrorNameRepeated = function() {
		$("#errorNameRepeated").show();
	}

	this.showErrorNameSpaces = function() {
		$("#errorNameSpaces").show();
	}

	this.showWarning = function() {
		$("#warning").show();
	}

	this.wipeForm = function() {
		$("#inputCommunityName").val("");
		$("#inputCommunityDescription").val("");
	}
};

function Controller(model, view) {
    this.init = function() {
        $("#createCommunityForm").bind("submit", function(event) {
			event.preventDefault();
			view.hideErrors();

			let data = view.loadFormData();
			data.token = model.getToken();

			let result = model.createCommunity(data);
			switch (result) {
				case "success":
					break;
				case "already_exists":
					view.showErrorNameRepeated();
					return;
				case "hasSpaces":
					view.showErrorNameSpaces();
					return;
				default:
					view.showError();
					return;
			}

			result = model.join(data);
			if (result == "success") view.showSuccess();
			else view.showWarning();
			view.wipeForm();
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

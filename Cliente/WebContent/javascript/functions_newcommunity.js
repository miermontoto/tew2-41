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
};

function Controller(model, view) {
    this.init = function() {
        $("#createCommunityForm").bind("submit", function(event) {
			let data = view.loadFormData();
			data.token = model.getToken();

			let result = model.createCommunity(data);
			switch (result) {
				case "success":
					break;
				case "already_exists":
					alert("Ya existe una comunidad con ese nombre");
					return;
				default:
					alert("Error desconocido");
					return;
			}

			result = model.join(data);
			if (result == "success") alert("Comunidad creada y unido a ella");
			else alert("Comunidad creada, error al unirse a ella");
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

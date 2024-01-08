function Model() {
	// Función que se comunica con el servidor, enviándole el usuario que se ha creado.
	this.createCommunity = function(community) {
		return CommunityServiceRs.create({
			$entity : community,
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
	let myModel = model;
	let myView = view;

    // Inicialización del modelo y la vista.
    this.init = function() {
        $("#createCommunityForm").bind("submit", function(event) {
            let response = model.createCommunity(view.loadFormData());

			response.success(function(data, status, jqXHR) {
				alert("Community created successfully");
				window.location.href = "index.html";
			});
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

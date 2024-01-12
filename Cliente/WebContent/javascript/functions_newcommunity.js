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
			console.log(data)
			if (model.createCommunity(data) === "success") {
				$("#mensajeError").hide();
				alert("Community created successfully");
				$("#iframe").attr("src", "home.html");
			} else {
				$("#mensajeError").show();
			}
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

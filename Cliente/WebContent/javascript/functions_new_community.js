function Model() {
	
	// Función que se comunica con el servidor, enviándole el usuario que se ha creado.
	this.createCommunity = function(community) {
		return CommunityServiceRs.create({
			$entity : community,
			$contentType : "application/json"});
	}
	
	
	
};

function View() {
	
	this.loadFormData = function() {
		var formData = {
				name : $("#inputCommunityName").val(), // Establezco mismo email y username, para mantener compatibilidad con sistema de users.
				description : $("#inputCommunityDescription").val()
		};
		return formData;
	}

};

function Controller(model, view) {
    // Inicialización del modelo y la vista.
    var model = model;
    var view = view;

    this.init = function() {
        $("#createCommunityForm").bind("submit", function(event) {
            var community = view.loadFormData(); 
            var response = model.createCommunity(user); 
            
            
            
        });
    }

   
}


// Se ejecuta al cargarse la página. Inicializa el modelo, vista, y finalmente, el controlador.
$(function() {
	var model = new Model();
	var view = new View();
	var control = new Controller(model, view);
	control.init();
});
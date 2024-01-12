function Model() {
	// Función que se comunica con el servidor, enviándole el usuario que se ha creado.
	this.reset = function(token) {

		return DatabaseServiceRs.reset({
			$entity : token,
			$contentType : "application/json"
		});
	}

	// Función para guardar el token en el almacenamiento local.
    this.setToken = function(token) {
	}

    this.getToken = function(){
    	sessionStorage.getItem("token"); // Obtengo el token para enviar al servidor
    }
};

function View() {

};

function Controller(model, view) {
    this.init = function() {
		$('#resetButton').on('click', function() {
			let token = model.getToken();
			model.reset(token); // Reseteo la BBDD con el viejo token.
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

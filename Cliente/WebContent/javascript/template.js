function Model() {

};

function View() {

};

function Controller(model, view) {
	let myModel = model;
	let myView = view;

    // Inicialización del modelo y la vista.
    this.init = function() {

    }
}

// Se ejecuta al cargarse la página. Inicializa el modelo, vista, y finalmente, el controlador.
$(function() {
	let model = new Model();
	let view = new View();
	let control = new Controller(model, view);
	control.init();
});

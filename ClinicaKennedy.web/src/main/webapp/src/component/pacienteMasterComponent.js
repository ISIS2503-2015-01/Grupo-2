define(['component/_pacienteMasterComponent'],function(_PacienteMasterComponent) {
    App.Component.PacienteMasterComponent = _PacienteMasterComponent.extend({
		postInit: function(){
			//Escribir en este servicio las instrucciones que desea ejecutar al inicializar el componente
		}
    });

    return App.Component.PacienteMasterComponent;
});
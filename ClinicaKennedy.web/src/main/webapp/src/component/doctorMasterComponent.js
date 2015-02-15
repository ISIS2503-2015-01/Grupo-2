define(['component/_doctorMasterComponent'],function(_DoctorMasterComponent) {
    App.Component.DoctorMasterComponent = _DoctorMasterComponent.extend({
		postInit: function(){
			//Escribir en este servicio las instrucciones que desea ejecutar al inicializar el componente
		}
    });

    return App.Component.DoctorMasterComponent;
});
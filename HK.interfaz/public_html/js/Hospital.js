(function () {
    var hospitalKennedy = angular.module('hospitalKennedy', []);

    hospitalKennedy.directive('toolbar', function () {
        return{
            restrict: 'E',
            templateUrl: 'partials/toolbar.html',
            controller: function () {
                this.tab = 0;
                this.selectTab = function (setTab) {
                    this.tab = setTab;
                };
                this.isSelected = function (tabParam) {
                    return this.tab === tabParam;
                };
            },
            controllerAs: 'toolbar'
        };
    });
    hospitalKennedy.directive('pacienteInfo', function () {
        return{
            restrict: 'E',
            templateUrl: 'partials/pacienteInfo.html',
            controller: ['$http', function ($http) {
                    var self = this;
                    console.log("Put a message here.");
                    self.pacientes = [{id: '12345678', nombre: 'Yolo', edad: '19', altura: '50'}, {id: '87654321', nombre: 'Yolo2', edad: '17', altura: '148'}];
                    //$http.get('http://localhost:8080/hospitalKennedy.servicios/webresources/Doctor/paciente').success(function(data){
                    //    self.pacientes=data;
                    //});
                }],
            controllerAs: 'getPacientes'
        };
    });

    hospitalKennedy.directive('nuevoPaciente', function () {
        return{
            restrict: 'E',
            templateUrl: 'index.html',
            controller: ['$http', function ($http) {
                    var self = this;
                    self.paciente = {};
                    this.addPaciente = function () {
                        $http.post('http://localhost:8080/hospitalKennedy.servicios/webresources/Doctor/agregar', JSON.stringify(self.paciente)).success(function (data) {
                            self.paciente = {};
                            toolbar.tab = 0;
                        });
                    };
                }],
            controllerAs: 'agregarPaciente'
        };
    });

    hospitalKennedy.directive('reportes', function () {
        return{
            restrict: 'E',
            templateUrl: 'partials/reportes.html',
            controller: ['$http', function ($http) {
                    var self = this;
                    console.log("Se busca los reportes del señor: " + idPaciente);
                    self.reportesPacientes = [{id: '12345678', actividadFisica: 'Yoloismo', alimentacion: "Carne", gravedad: "gravisima", fechaCreacion: "2015/04/03", localizacionDolor: "detras de la cabeza", patronSuenio: "poco sueño", medicamentosRecientes: "Vicodin"}];
                    //$http.get('http://localhost:8080/hospitalKennedy.servicios/webresources/Doctor/paciente').success(function(data){
                    //    self.pacientes=data;
                    //});

                }],
            controllerAs: 'getReportes'
        };
    });

    hospitalKennedy.controller('pacientesActuales', function () {
        var pacienteActual;
        var pacientes;
        this.asignarPacienteActual = function (nPaciente) {
            pacienteActual = nPaciente;
            toolbar.selectTab(4);
        };
        this.asignarPacientes = function (nPacientes) {
            pacientes = nPacientes;
        };
    });

})();
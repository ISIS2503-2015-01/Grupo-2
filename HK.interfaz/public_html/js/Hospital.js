(function () {
    var hospitalKennedy = angular.module('hospitalKennedy', []);
    var pacienteActual;
    var token;
    var certificado;
    var paciente1 = new Object;
    paciente1.cedulaCiudadania = '1010852741';
    paciente1.nombre = 'Francisco';
    paciente1.edad = 23;
    paciente1.altura = 172;
    var paciente2 = new Object;
    paciente2.cedulaCiudadania = '1010741963';
    paciente2.nombre = 'Carlos';
    paciente2.edad = 26;
    paciente2.altura = 168;
    var listaPacientes = [paciente1, paciente2];

    var reporte1 = new Object;
    reporte1 = {id: 1, actividadFisica: 'Trotar', alimentacion: "Carne",
        gravedad: "Normal", fechaCreacion: "2015/04/03", localizacionDolor: "detras de la cabeza", patronSuenio: "poco sueño",
        medicamentosRecientes: "Vicodin"};
    var reporte2 = new Object;
    reporte2 = {id: 2, actividadFisica: 'Estar sentado', alimentacion: "Dulces", gravedad: "Grave",
        fechaCreacion: "2015/04/09", localizacionDolor: "en la frente", patronSuenio: "poco sueño", medicamentosRecientes: "Acetaminofen"};
    var reporte3 = new Object;
    reporte3 = {id: 3, actividadFisica: 'Trotar', alimentacion: "Carne",
        gravedad: "Muy grave", fechaCreacion: "2015/04/15", localizacionDolor: "detras de la cabeza", patronSuenio: "poco sueño",
        medicamentosRecientes: "Vicodin"};
    var reporte4 = new Object;
    reporte4 = {id: 4, actividadFisica: 'Estar sentado', alimentacion: "Dulces", gravedad: "Normal",
        fechaCreacion: "2015/04/21", localizacionDolor: "en la frente", patronSuenio: "poco sueño", medicamentosRecientes: "Acetaminofen"};

    paciente1.reportes = [reporte1, reporte2, reporte3, reporte4];
    var idn = 4;
    var gravedades = ["Normal", "Grave", "Muy Grave"];
    paciente2.reportes = [];
    for (i = 0; i < 30; i++) {
        var num = Math.round(Math.random() * 2);
        var fecha = Math.round(Math.random() * 30);
        var idn = idn + 1;
        var reporte = {id: idn, actividadFisica: 'Estar sentado', alimentacion: "Dulces", gravedad: gravedades[num],
            fechaCreacion: "2015/04/" + fecha, localizacionDolor: "en la frente", patronSuenio: "poco sueño", medicamentosRecientes: "Acetaminofen"};
        paciente2.reportes.push(reporte);
    }

    hospitalKennedy.directive('toolbar', function () {
        return{
            restrict: 'E',
            templateUrl: 'partials/toolbar.html',
            controller: ['$http', function ($http) {
                    this.tab = 0;
                    this.credenciales = {username: '', password: ''};
                    this.selectTab = function (setTab) {
                        this.tab = setTab;
                    };
                    this.isSelected = function (tabParam) {
                        return this.tab === tabParam;
                    };
                    this.hacerLogin = function () {

                        console.log("Haciendo login");
                        console.log(this.credenciales.username + " " + this.credenciales.password + " Credenciales: " + this.credenciales);
                        console.log(JSON.stringify(this.credenciales, ['username', 'password']));
                        certi = $http.post('http://172.24.99.164:80/webresources/auth/login', JSON.stringify(this.credenciales, ['username', 'password'])).success(function (data) {
                            console.log(certi);
                            console.log(certi.$$state.value.data);
                            token = certi.$$state.value.data;
                        });
                        setTimeout(function () {
                            window.alert("Usted se ha logeado exitosamente")
                        }, 1000);
                    };
                }],
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
                    self.pacientes = listaPacientes;
                    $http.get('http://172.24.99.164:80/hospitalKennedy.servicios/webresources/Doctor/paciente').success(function (data) {
                        self.pacientes.push(data);
                    });
                    console.log(self.pacientes);
                }],
            controllerAs: 'getPacientes'
        };
    });
    hospitalKennedy.directive('nuevoPaciente', function () {
        return{
            restrict: 'E',
            templateUrl: 'partials/nuevo-paciente.html',
            controller: ['$http', function ($http) {
                    var self = this;
                    self.paciente = {};
                    this.addPaciente = function () {

                        listaPacientes.push(self.paciente);
                        console.log("::::::::::::::::::::::::::::----------------:::::::::::::::::::: ENTRO AL METODO PARA AGREGAR PACIENTE");
                        console.log(listaPacientes);

                        $http.post('http://172.24.99.164:80/hospitalKennedy.servicios/webresources/Doctor/agregarPaciente', JSON.stringify(self.paciente)).success(function (data) {
                            console.log("HIZO EL METODO :) :) :) :) :) :) :) ")
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
                    self.idActual = new String;
                    console.log("Se busca los reportes del señor: " + idPaciente);
                    self.reportesPacientes = [];
                    self.reporte = {};
                    this.setId = function (id) {
                        self.idActual = id;
                        console.log("Id set to: " + id);
                        self.reportesPacientes = this.darReportesDeId(id);
                    }
                    this.getId=function(){
                        return self.idActual;
                    }
                    this.darReportesDeId = function (id) {
                        console.log("Busca los reportes del id " + id);
                        //console.log('http://localhost:8080/hospitalKennedy.servicios/webresources/Pacientes/'+id+'/reportes/');
                        $http.get('http://172.24.99.164:80/hospitalKennedy.servicios/webresources/Pacientes/' + id + '/reportes/').success(function (data) {
                            self.reportesPacientes = data;
                        });
                        console.log("va a buscar reportes de " + id);
                        for (i = 0; i < listaPacientes.length; i++) {
                            if (listaPacientes[i].cedulaCiudadania.localeCompare(id) === 0) {
                                console.log(listaPacientes[i].reportes);
                                self.reportesPacientes = listaPacientes[i].reportes;
                                return listaPacientes[i].reportes;
                            }
                        }

                        return self.reportesPacientes;
                    };
                    this.darReportesEntreFechas = function (id, fecha1, fecha2) {
                        console.log("Se buscan los reportes entre fechas del id "+id)
                        $http.get('http://172.24.99.164:80/hospitalKennedy.servicios/webresources/Pacientes/' + id + '/reportes/' + fecha1 + '/' + fecha2).success(function (data) {
                            self.reportesPacientes = data;
                        });
                        var res = [];
                        for (i = 0; i < listaPacientes.length; i++) {
                            if (listaPacientes[i].cedulaCiudadania.localeCompare(id) === 0) {
                                console.log("Encontro al paciente " +i);
                                var reports = listaPacientes[i].reportes;
                                for (j = 0; j < reports.length; j++){
                                    if(reports[j].fechaCreacion.localeCompare(fecha1)>= 1 && reports[j].fechaCreacion.localeCompare(fecha2) <= -1)
                                    res.push(reports[j]);
                                }

                            }
                        }
                        res.sort(function(a,b){
                            a.fechaCreacion.localeCompare(b.fechaCreacion)});
                        self.reportesPacientes = res;
                        console.log(res);
                        return self.reportesPacientes;
                    };
                    this.darDetalles = function (idPaciente, idReporte) {
                        console.log('entroo a dar detalles del paciente: ' + idPaciente + ' sobre el reporte: ' + idReporte);
                        $http.get('http://172.24.99.164:80/hospitalKennedy.servicios/webresources/Pacientes/' + idPaciente + '/reportes/' + idReporte).success(function (data) {
                            self.reporte = data;
                        });
                        for (i = 0; i < listaPacientes.length; i++) {
                            if (listaPacientes[i].cedulaCiudadania.localeCompare(id) === 0) {
                                var reportesP = listaPacientes[i].reportes;
                                for (j = 0; j < reportesP.lenght; j++) {
                                    if (reportesP[j].id.localeCompare(idReporte) === 0) {
                                        self.reporte = reportesP[j];
                                    }
                                }

                            }
                        }
                    };
                    this.darReporte = function () {
                        return self.reporte;
                    };
                }],
            controllerAs: 'getReportes'
        };
    });
    hospitalKennedy.controller('pacientesActuales', function () {
        var pacientes;
        console.log("Entro al metodo, al menos");
        function asignarPacienteActual(nPaciente) {
            pacienteActual = nPaciente;
            console.log("HUEHUEHUEHUEHUEHUE");
        }
        ;
        this.asignarPacientes = function (nPacientes) {
            pacientes = nPacientes;
        };
        this.darActual = function () {
            return pacienteActual;
        };
    });
    hospitalKennedy.directive('nuevoReporte', function () {
        return{
            restrict: 'E',
            templateUrl: 'partials/nuevo-reporte.html',
            controller: ['$http', function ($http) {
                    var self = this;
                    self.reporte = {};
                    this.addReporte = function (id) {
                        console.log("Entra a metodo de agregar Reporte");
                        $http.post('http://172.24.99.164:80/hospitalKennedy.servicios/webresources/Pacientes/10203040/agregarReportes/', JSON.stringify(self.reporte)).success(function (data) {
                            console.log("Metodo check");
                            self.reporte = {};
                            toolbar.tab = 0;
                        });
                    };
                }],
            controllerAs: 'agregarReporte'
        };
    });
    hospitalKennedy.directive('detallesReporte', function () {
        return{
            restrict: 'E',
            templateUrl: 'partials/detallesReporte.html',
            controller: ['$http', function ($http) {
                    var self = this;
                    self.reporte = [];
                    sself.reporte = getReportes.darReporte;
                    console.log('El reporte a dar detalles es ' + self.reporte);
                    this.setReporte = function(reporte){
                        self.reporte = reporte;
                        
                    }
                    this.getReporte = function(){
                        return self.reporte();
                    }
                }],
            controllerAs: 'detallesReporte'
        };
    });
})();

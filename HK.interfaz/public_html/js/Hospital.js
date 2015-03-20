(function(){var hospitalKennedy= angular.module('hospitalKennedy',[]);
    
hospitalKennedy.directive('toolbar', function(){
        return{
            restrict:'E',
            templateUrl: 'partials/toolbar.html',
            controller:function(){
                this.tab=0;
                this.selectTab=function(setTab){
                    this.tab=setTab;
                };
                this.isSelected=function(tabParam){
                    return this.tab===tabParam;
                };
            },
            controllerAs:'toolbar'
        };
    });
hospitalKennedy.directive('pacienteInfo', function(){
        return{
            restrict:'E',
            templateUrl:'partials/paciente-info.html',
            controller: ['$http',function($http){
                var self=this;
                self.pacientes=[];
                    $http.get('http://localhost:8080/hospitalKennedy.servicios/webresources/paciente/').success(function(data){
                        self.pacientes=data;
                    });
            }],
            controllerAs:'getPacientes'
        };
    });
})();
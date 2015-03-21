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
            templateUrl:'index.html',
            controller: ['$http',function($http){
                var self=this;
                self.pacientes=[{id:'12345678', nombre:'Yolo', edad:'19', altura:'50'},{id:'87654321', nombre:'Yolo2', edad:'17', altura:'148'}];
                    $http.get('http://localhost:8080/hospitalKennedy.servicios/webresources/Doctor/paciente').success(function(data){
                        self.pacientes=data;
                    });
                
            }],
            controllerAs:'getPacientes'
        };
    });
})();
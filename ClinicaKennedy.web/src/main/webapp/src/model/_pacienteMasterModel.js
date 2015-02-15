define([], function() {
    App.Model._PacienteMasterModel = Backbone.Model.extend({
     	initialize: function() {
            this.on('invalid', function(model,error) {
                Backbone.trigger('paciente-master-model-error', error);
            });
        },
        validate: function(attrs, options){
        	var modelMaster = new App.Model.PacienteModel();
        	if(modelMaster.validate){
            	return modelMaster.validate(attrs.pacienteEntity,options);
            }
        }
    });

    App.Model._PacienteMasterList = Backbone.Collection.extend({
        model: App.Model._PacienteMasterModel,
        initialize: function() {
        }

    });
    return App.Model._PacienteMasterModel;
    
});
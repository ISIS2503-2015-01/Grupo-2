define([], function() {
    App.Model._DoctorMasterModel = Backbone.Model.extend({
     	initialize: function() {
            this.on('invalid', function(model,error) {
                Backbone.trigger('doctor-master-model-error', error);
            });
        },
        validate: function(attrs, options){
        	var modelMaster = new App.Model.DoctorModel();
        	if(modelMaster.validate){
            	return modelMaster.validate(attrs.doctorEntity,options);
            }
        }
    });

    App.Model._DoctorMasterList = Backbone.Collection.extend({
        model: App.Model._DoctorMasterModel,
        initialize: function() {
        }

    });
    return App.Model._DoctorMasterModel;
    
});
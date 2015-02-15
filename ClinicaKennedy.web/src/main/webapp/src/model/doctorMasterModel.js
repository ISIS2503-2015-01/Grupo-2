define(['model/_doctorMasterModel'], function() { 
    App.Model.DoctorMasterModel = App.Model._DoctorMasterModel.extend({

    });

    App.Model.DoctorMasterList = App.Model._DoctorMasterList.extend({
        model: App.Model.DoctorMasterModel
    });

    return  App.Model.DoctorMasterModel;

});
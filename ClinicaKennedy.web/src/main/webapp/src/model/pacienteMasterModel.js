define(['model/_pacienteMasterModel'], function() { 
    App.Model.PacienteMasterModel = App.Model._PacienteMasterModel.extend({

    });

    App.Model.PacienteMasterList = App.Model._PacienteMasterList.extend({
        model: App.Model.PacienteMasterModel
    });

    return  App.Model.PacienteMasterModel;

});
define(['controller/selectionController', 'model/cacheModel', 'model/doctorMasterModel', 'component/_CRUDComponent', 'controller/tabController', 'component/doctorComponent',
 'component/pacienteComponent'],
 function(SelectionController, CacheModel, DoctorMasterModel, CRUDComponent, TabController, DoctorComponent,
 pacienteDelDoctorComponent) {
    App.Component._DoctorMasterComponent = App.Component.BasicComponent.extend({
        initialize: function() {
            var self = this;
            this.configuration = App.Utils.loadComponentConfiguration('doctorMaster');
            App.Model.DoctorMasterModel.prototype.urlRoot = this.configuration.context;
            this.componentId = App.Utils.randomInteger();
            
            this.masterComponent = new DoctorComponent();
            this.masterComponent.initialize();
            
            this.childComponents = [];
			
			this.initializeChildComponents();
            
            Backbone.on(this.masterComponent.componentId + '-post-doctor-create', function(params) {
                self.renderChilds(params);
            });
            Backbone.on(this.masterComponent.componentId + '-post-doctor-edit', function(params) {
                self.renderChilds(params);
            });
            Backbone.on(this.masterComponent.componentId + '-pre-doctor-list', function() {
                self.hideChilds();
            });
            Backbone.on('doctor-master-model-error', function(error) {
                Backbone.trigger(uComponent.componentId + '-' + 'error', {event: 'doctor-master-save', view: self, message: error});
            });
            Backbone.on(this.masterComponent.componentId + '-instead-doctor-save', function(params) {
                self.model.set('doctorEntity', params.model);
                if (params.model) {
                    self.model.set('id', params.model.id);
                } else {
                    self.model.unset('id');
                }

				App.Utils.fillCacheList(
					'pacienteDelDoctor',
					self.model,
					self.pacienteDelDoctorComponent.getDeletedRecords(),
					self.pacienteDelDoctorComponent.getUpdatedRecords(),
					self.pacienteDelDoctorComponent.getCreatedRecords()
				);

                self.model.save({}, {
                    success: function() {
                        Backbone.trigger(self.masterComponent.componentId + '-' + 'post-doctor-save', {view: self, model : self.model});
                    },
                    error: function(error) {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'doctor-master-save', view: self, error: error});
                    }
                });
			    if (this.postInit) {
					this.postInit();
				}
            });
        },
        render: function(domElementId){
			if (domElementId) {
				var rootElementId = $("#"+domElementId);
				this.masterElement = this.componentId + "-master";
				this.tabsElement = this.componentId + "-tabs";

				rootElementId.append("<div id='" + this.masterElement + "'></div>");
				rootElementId.append("<div id='" + this.tabsElement + "'></div>");
			}
			this.masterComponent.render(this.masterElement);
		},
		initializeChildComponents: function () {
			this.tabModel = new App.Model.TabModel({tabs: [
                {label: "Paciente Del Doctor", name: "pacienteDelDoctor", enable: true}
			]});
			this.tabs = new TabController({model: this.tabModel});

			this.pacienteDelDoctorComponent = new pacienteDelDoctorComponent();
            this.pacienteDelDoctorComponent.initialize({cache: {data: [], mode: "memory"},pagination: false});
			this.childComponents.push(this.pacienteDelDoctorComponent);

            var self = this;
            
            this.configToolbar(this.pacienteDelDoctorComponent,false);
            this.pacienteDelDoctorComponent.disableEdit();

            Backbone.on(this.pacienteDelDoctorComponent.componentId + '-toolbar-add', function() {
                var selection = new SelectionController({"componentId":"pacienteDelDoctorComponent"});
                App.Utils.getComponentList('pacienteComponent', function(componentName, model) {
                    if (model.models.length == 0) {
                        alert('There is no Paciente Del Doctors to select.');
                    } else {
                        selection.showSelectionList({list: model, name: 'name', title: 'Paciente Del Doctor List'});
                    }
                    ;
                });
            });

            Backbone.on('pacienteDelDoctorComponent-post-selection', function(models) {
            	self.pacienteDelDoctorComponent.addRecords(models);
            	self.pacienteDelDoctorComponent.render();
            });

		},
        renderChilds: function(params) {
            var self = this;
            
            var options = {
                success: function() {
                	self.tabs.render(self.tabsElement);

					self.pacienteDelDoctorComponent.clearCache();
					self.pacienteDelDoctorComponent.setRecords(self.model.get('listpacienteDelDoctor'));
					self.pacienteDelDoctorComponent.render(self.tabs.getTabHtmlId('pacienteDelDoctor'));

                    $('#'+self.tabsElement).show();
                },
                error: function() {
                    Backbone.trigger(self.componentId + '-' + 'error', {event: 'doctor-edit', view: self, id: id, data: data, error: error});
                }
            };
            if (params.id) {
                self.model = new App.Model.DoctorMasterModel({id: params.id});
                self.model.fetch(options);
            } else {
                self.model = new App.Model.DoctorMasterModel();
                options.success();
            }


        },
        showMaster: function (flag) {
			if (typeof (flag) === "boolean") {
				if (flag) {
					$("#"+this.masterElement).show();
				} else {
					$("#"+this.masterElement).hide();
				}
			}
		},
        hideChilds: function() {
            $("#"+this.tabsElement).hide();
        },
		configToolbar: function(component, composite) {
		    component.removeGlobalAction('refresh');
			component.removeGlobalAction('print');
			component.removeGlobalAction('search');
			if (!composite) {
				component.removeGlobalAction('create');
				component.removeGlobalAction('save');
				component.removeGlobalAction('cancel');
				component.addGlobalAction({
					name: 'add',
					icon: 'glyphicon-send',
					displayName: 'Add',
					show: true
				}, function () {
					Backbone.trigger(component.componentId + '-toolbar-add');
				});
			}
        },
        getChilds: function(name){
			for (var idx in this.childComponents) {
				if (this.childComponents[idx].name === name) {
					return this.childComponents[idx].getRecords();
				}
			}
		},
		setChilds: function(childName,childData){
			for (var idx in this.childComponents) {
				if (this.childComponents[idx].name === childName) {
					this.childComponents[idx].setRecords(childData);
				}
			}
		},
		renderMaster: function(domElementId){
			this.masterComponent.render(domElementId);
		},
		renderChild: function(childName, domElementId){
			for (var idx in this.childComponents) {
				if (this.childComponents[idx].name === childName) {
					this.childComponents[idx].render(domElementId);
				}
			}
		}
    });

    return App.Component._DoctorMasterComponent;
});
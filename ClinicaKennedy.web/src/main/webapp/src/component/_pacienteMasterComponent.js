define(['controller/selectionController', 'model/cacheModel', 'model/pacienteMasterModel', 'component/_CRUDComponent', 'controller/tabController', 'component/pacienteComponent',
 'component/reporteComponent'],
 function(SelectionController, CacheModel, PacienteMasterModel, CRUDComponent, TabController, PacienteComponent,
 reporteDelPacienteComponent) {
    App.Component._PacienteMasterComponent = App.Component.BasicComponent.extend({
        initialize: function() {
            var self = this;
            this.configuration = App.Utils.loadComponentConfiguration('pacienteMaster');
            App.Model.PacienteMasterModel.prototype.urlRoot = this.configuration.context;
            this.componentId = App.Utils.randomInteger();
            
            this.masterComponent = new PacienteComponent();
            this.masterComponent.initialize();
            
            this.childComponents = [];
			
			this.initializeChildComponents();
            
            Backbone.on(this.masterComponent.componentId + '-post-paciente-create', function(params) {
                self.renderChilds(params);
            });
            Backbone.on(this.masterComponent.componentId + '-post-paciente-edit', function(params) {
                self.renderChilds(params);
            });
            Backbone.on(this.masterComponent.componentId + '-pre-paciente-list', function() {
                self.hideChilds();
            });
            Backbone.on('paciente-master-model-error', function(error) {
                Backbone.trigger(uComponent.componentId + '-' + 'error', {event: 'paciente-master-save', view: self, message: error});
            });
            Backbone.on(this.masterComponent.componentId + '-instead-paciente-save', function(params) {
                self.model.set('pacienteEntity', params.model);
                if (params.model) {
                    self.model.set('id', params.model.id);
                } else {
                    self.model.unset('id');
                }

				App.Utils.fillCacheList(
					'reporteDelPaciente',
					self.model,
					self.reporteDelPacienteComponent.getDeletedRecords(),
					self.reporteDelPacienteComponent.getUpdatedRecords(),
					self.reporteDelPacienteComponent.getCreatedRecords()
				);

                self.model.save({}, {
                    success: function() {
                        Backbone.trigger(self.masterComponent.componentId + '-' + 'post-paciente-save', {view: self, model : self.model});
                    },
                    error: function(error) {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'paciente-master-save', view: self, error: error});
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
                {label: "Reporte Del Paciente", name: "reporteDelPaciente", enable: true}
			]});
			this.tabs = new TabController({model: this.tabModel});

			this.reporteDelPacienteComponent = new reporteDelPacienteComponent();
            this.reporteDelPacienteComponent.initialize({cache: {data: [], mode: "memory"},pagination: false});
			this.childComponents.push(this.reporteDelPacienteComponent);

            var self = this;
            
            this.configToolbar(this.reporteDelPacienteComponent,true);
            Backbone.on(self.reporteDelPacienteComponent.componentId + '-post-reporte-create', function(params) {
                params.view.currentModel.setCacheList(params.view.currentList);
            });
            
		},
        renderChilds: function(params) {
            var self = this;
            
            var options = {
                success: function() {
                	self.tabs.render(self.tabsElement);

					self.reporteDelPacienteComponent.clearCache();
					self.reporteDelPacienteComponent.setRecords(self.model.get('listreporteDelPaciente'));
					self.reporteDelPacienteComponent.render(self.tabs.getTabHtmlId('reporteDelPaciente'));

                    $('#'+self.tabsElement).show();
                },
                error: function() {
                    Backbone.trigger(self.componentId + '-' + 'error', {event: 'paciente-edit', view: self, id: id, data: data, error: error});
                }
            };
            if (params.id) {
                self.model = new App.Model.PacienteMasterModel({id: params.id});
                self.model.fetch(options);
            } else {
                self.model = new App.Model.PacienteMasterModel();
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

    return App.Component._PacienteMasterComponent;
});
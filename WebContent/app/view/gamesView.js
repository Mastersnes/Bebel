/*global define */
define(["jquery",
        'underscore',
        "app/utils/viewUtils",
        "text!app/template/games.html"], 
function($, _, ViewUtils, page) {
	'use strict';

	return function(parent) {
		this.init = function(parent) {
			this.el = $("#games");
			this.parent = parent;
			this.render();
		};

		this.render = function() {
			_.templateSettings.variable = "data";
			var template = _.template(page);
			var templateData = {};
			this.el.html(template(templateData));
			
			ViewUtils.verticalCenter();
			
			this.checkEvents();
		};
		
		this.checkEvents = function() {
			var that = this;
			$("#connexion").click(function() {
				MessageUtils.hide();
				that.model.send(function(data) {
					if (data.codeRetour != 0) {
						MessageUtils.show(data.message, "danger");
					}else {
						console.log("success");
					}
				});
			});
			$("#inscription").click(function() {
				if (!that.inscriptionView) {
					that.inscriptionView = new InscriptionView(that);
				}
				that.inscriptionView.show();
			});
		};
		
		this.init(parent);
	};
});
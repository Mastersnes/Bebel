/*global define */
define(["jquery",
        'underscore',
        "app/utils/utils",
        "app/utils/messageUtils",
        "text!app/template/main.html",
        "app/model/checkMailModel"], 
function($, _, Utils, MessageUtils, page, Model) {
	'use strict';

	return function() {
		this.init = function() {
			this.el = $("#app");
			this.render();
			
			var origin = Utils.getUrlParameter("origin");
			if (origin == "check") this.checkMail();
		};

		this.render = function() {
			_.templateSettings.variable = "data";
			var template = _.template(page);
			var templateData = {};
			this.el.html(template(templateData));
		};
		
		this.checkMail = function() {
			var model = new Model();
			model.send(function(data) {
				if (data.codeRetour != 0) {
					MessageUtils.show(data.message, "danger");
				}else {
					MessageUtils.show(data.message, "success");
				}
			});
		};
		
		this.init();
	};
});
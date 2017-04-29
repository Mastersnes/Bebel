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
			$("#bul").click(function() {
				window.open("http://lesjeuxdebebel.fr.nf/BUL", "_blank");
			});
			$("#samhain").click(function() {
				window.open("http://lesjeuxdebebel.fr.nf/Samhain", "_blank");
			});
		};
		
		this.init(parent);
	};
});
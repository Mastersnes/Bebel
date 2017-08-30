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
			$("#retour").click(function() {
				$(".flash-game embed").attr("src", "");
				$(".flash-game").hide();
				$(".game-list").show();
			});
			$("#bul").click(function() {
				alert("En construction");
			});
			$("#samhain").click(function() {
				window.open("http://lesjeuxdebebel.fr.nf/Samhain", "_blank");
			});
			$("#heritage").click(function() {
				$(".flash-game embed").attr("src", "app/swf/heritage.swf");
				$(".flash-game").show();
				$(".game-list").hide();
			});
			$("#broc-and-house").click(function() {
				console.log("here");
				$(".flash-game embed").attr("src", "app/swf/broc-and-house.swf");
				$(".flash-game").show();
				$(".game-list").hide();
			});
			$("#pieceOfCake").click(function() {
			    window.open("https://les-jeux-de-bebel.itch.io/piece-of-cake", "_blank");
            });
			$("#slimer").click(function() {
                window.open("https://les-jeux-de-bebel.itch.io/slimer", "_blank");
            });
		};
		
		this.init(parent);
	};
});
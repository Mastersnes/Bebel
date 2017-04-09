'use strict';
define(["jquery", "app/utils/viewUtils"], function($, ViewUtils){
	return {
		show : function(message, type) {
			$("#menu-msg").removeClass("alert-success");
			$("#menu-msg").removeClass("alert-info");
			$("#menu-msg").removeClass("alert-warning");
			$("#menu-msg").removeClass("alert-danger");

			$("#menu-msg").addClass("alert-" + type);
			
			$("#menu-msg").html(message);
			$("#menu-msg").show();
			ViewUtils.verticalCenter();
		},
		hide : function() {
			$("#menu-msg").hide();
			ViewUtils.verticalCenter();
		}
	};
});
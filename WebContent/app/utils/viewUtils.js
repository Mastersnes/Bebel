'use strict';
define(["jquery"], function($){
	return {
		/**
		* Centre verticalement en javascript
		**/
		verticalCenter : function() {
			$(".js-center").each(function(index, value) {
				var me = $(value);
				var pere = $(me.parent());
				var topPosition = pere.offset().top + (pere.height()/2) - (me.height()/2);
				me.offset({top : topPosition});
				console.log(topPosition);
			});
		}
	};
});
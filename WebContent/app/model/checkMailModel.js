/*global define */
define(["app/utils/utils"], 
function(Utils) {
	'use strict';

	return function() {
		this.data = {
				"mail" : "mail",
				"token" : "token"
		};
		
		this.send = function(successFunc) {
			this.data.mail = Utils.getUrlParameter("mail");
			this.data.token = Utils.getUrlParameter("token");
			Utils.load("checkSubscribe", this.data, successFunc);
		};
	};
});
define("jquery underscore app/utils/utils app/utils/viewUtils app/utils/map text!app/template/game/quetes.html app/data/quetes".split(" "),function(b,e,f,k,n,g,h){return function(m){this.init=function(a){this.el=b(".quetes");this.parent=a;this.Textes=a.Textes;this.mediatheque=a.mediatheque;this.kongregateUtils=a.kongregateUtils;this.saveManager=a.saveManager;this.recompenseManager=a.recompenseManager;this.player=a.playerManager;this.quetesOpen=!1;this.el.hide()};this.open=function(a){this.quetesOpen=
!0;this.onReturn=a;b(".histoire").fadeOut();b(".fight").fadeOut();b(".jeuGarde").fadeOut();b(".boutique").fadeOut();this.list();this.el.fadeIn()};this.list=function(){var a=this;this.quetes=h.list(this.player);this.render();this.el.find(".actionsContainer #quitter").show();this.el.find(".actionsContainer #accepter").hide();this.el.find(".actionsContainer #retour").hide();this.el.find("storyContainer#details").hide();this.el.find("storyContainer#nothing").fadeIn();this.el.find(".quetesContainer").fadeIn();
k.verticalCenter();f.then(function(){a.showActions()},50)};this.details=function(){var a=this,c=this.currentQuete,d=this.el.find("storyContainer#details");d.find("titre").html(this.Textes.get(c.name));var e=d.find("textes");e.empty();for(var g in c.description){var h=c.description[g],l=b("\x3ctexte\x3e\x3c/texte\x3e");l.html(this.Textes.get(h));e.append(l)}d.find("#recompense .val").html(c.price);d.find("#difficulte .val").html(this.Textes.get(c.difficulty));this.el.find(".actionsContainer #quitter").hide();
this.el.find(".actionsContainer #accepter").show();this.el.find(".actionsContainer #retour").show();this.el.find("storyContainer#details").fadeIn();this.el.find("storyContainer#nothing").hide();this.el.find(".quetesContainer").hide();k.verticalCenter();f.then(function(){a.showActions()},50)};this.render=function(){e.templateSettings.variable="data";var a=e.template(g);this.el.html(a({text:this.Textes,quetes:this.quetes}));this.makeEvents()};this.loop=function(){this.quetesOpen&&(b("carnet").hasClass("hide")||
b("carnet").addClass("hide"),b("loupe").hasClass("hide")||b("loupe").addClass("hide"))};this.showActions=function(a){var b=this;this.el.find("action").css({top:"0%"});f.then(function(){b.el.find(".quetesContainer").css("overflow","auto");a&&a()},500)};this.hideActions=function(a){this.el.find(".quetesContainer").removeAttr("style");this.el.find("action").css({top:"150%"});this.el.find("storyContainer#details").fadeOut();f.then(function(){a&&a()},500)};this.makeEvents=function(){var a=this;this.el.find(".quetesContainer action").click(function(){var c=
parseInt(b(this).attr("id")),d=a.quetes[c];d&&a.hideActions(function(){a.currentQuete=d;a.details()})});this.el.find("#quitter").click(function(){a.hideActions(function(){a.close();a.onReturn()})});this.el.find("#accepter").click(function(){a.hideActions(function(){a.close();a.parent.histoireView.startQuest(a.currentQuete)})});this.el.find("#retour").click(function(){a.hideActions(function(){a.currentQuete=null;a.list()})})};this.close=function(){this.quetesOpen=!1};this.init(m)}});
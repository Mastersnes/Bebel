define(["jquery","underscore","app/utils/utils","app/utils/viewUtils","text!app/template/menu/popup/option.html"],function(c,d,g,h,e){return function(f){this.init=function(a){this.el=c(".option-popup");this.parent=a;this.Textes=a.Textes;this.mediatheque=a.mediatheque;this.saveManager=a.saveManager;this.render();this.el.hide()};this.render=function(){d.templateSettings.variable="data";var a=d.template(e);this.el.html(a({text:this.Textes}));this.makeEvents()};this.refreshTextes=function(){this.el.find("son#sound text").html(this.Textes.get("sound"));
this.el.find("son#music text").html(this.Textes.get("music"));this.el.find("flags text").html(this.Textes.get("language"));this.el.find("element#selectAuto span").html(this.Textes.get("selectAuto"));this.el.find("element#cibleUnique span").html(this.Textes.get("cibleUnique"))};this.show=function(){this.refresh();this.el.fadeIn()};this.refresh=function(){this.mediatheque.isMute("sound")?this.el.find("son#sound").addClass("mute"):this.el.find("son#sound").removeClass("mute");this.mediatheque.isMute("music")?
this.el.find("son#music").addClass("mute"):this.el.find("son#music").removeClass("mute");var a=this.Textes.local;this.el.find("flag").removeClass("selected");this.el.find("flag#"+a).addClass("selected");this.saveManager.getOption("selectAuto")?this.el.find("#selectAuto case").addClass("coche"):this.el.find("#selectAuto case").removeClass("coche");this.saveManager.getOption("cibleUnique")?this.el.find("#cibleUnique case").addClass("coche"):this.el.find("#cibleUnique case").removeClass("coche")};this.makeEvents=
function(){var a=this;this.el.find(".canClose").click(function(b){c(b.target).hasClass("canClose")&&(a.mediatheque.playSound("ui/fermerPopup.wav"),a.el.fadeOut())});this.el.find("flag").click(function(b){c(this).hasClass("selected")||(b=c(this).attr("id"),a.Textes.setLanguage(b),a.parent.refreshTextes(),a.refresh())});this.el.find("son#sound").click(function(b){a.mediatheque.mute("sound");a.refresh();a.parent.refresh()});this.el.find("son#music").click(function(b){a.mediatheque.mute("music");a.refresh();
a.parent.refresh()});this.el.find("#selectAuto").click(function(b){a.saveManager.setOption("selectAuto",!a.saveManager.getOption("selectAuto"));a.refresh()});this.el.find("#cibleUnique").click(function(b){a.saveManager.setOption("cibleUnique",!a.saveManager.getOption("cibleUnique"));a.refresh()})};this.init(f)}});
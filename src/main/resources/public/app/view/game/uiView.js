define(["jquery","underscore","app/utils/utils","text!app/template/game/ui.html"],function(h,g,k,l){return function(m){this.init=function(a){this.el=h(".ui");this.parent=a;this.Textes=a.Textes;this.mediatheque=a.mediatheque;this.kongregateUtils=a.kongregateUtils;this.saveManager=a.saveManager;this.player=a.playerManager};this.render=function(){g.templateSettings.variable="data";var a=g.template(l);this.el.html(a({text:this.Textes}))};this.loop=function(a){a=this.player.get("life");this.refresh("fiole.life",
a.current,a.max,!0,11);this.refreshEtats();this.player.get("unlockMana")?(a=this.player.get("mana"),this.el.find("fiole.mana").fadeIn(),this.refresh("fiole.mana",a.current,a.max,!0,11)):this.el.find("fiole.mana").hide();a=this.player.get("gold");this.refresh("gold",a,500,!1,8);0<a?this.el.find("gold montant").show():this.el.find("gold montant").hide();a=this.player.get("xp");var b=this.player.levelManager.nextPalier();this.refresh("level",a,b,!1,19);a=this.player.get("level");10>a&&(a="0"+a);this.el.find("level montant").html(a)};
this.refresh=function(a,b,f,d,e){if(f){this.el.find(a).fadeIn();e--;var c=b;d?(c=Math.round(k.toPercent(b,f)),this.el.find(a+" montant").html(c+"%")):this.el.find(a+" montant").html(c);d=Math.floor(Math.log(c)/Math.LN10+1);0==c&&(d=1);this.el.find(a+" montant").attr("digits",d);b=Math.ceil(b*e/f);b>e&&(b=e);this.el.find(a+" \x3e picture").attr("step",b)}else this.el.find(a).hide()};this.refreshEtats=function(){var a=this.player.get("debuff");a?this.el.find("fiole.life").attr("debuff",a.element):this.el.find("fiole.life").removeAttr("debuff");
(a=this.player.get("buff"))?(this.el.find("fiole.life buff").attr("class",a.element),a=Math.ceil(5*a.current/a.duree),5<a&&(a=5),this.el.find("fiole.life buff \x3e picture").attr("step",a),this.el.find("fiole.life buff").show()):this.el.find("fiole.life buff").hide()};this.init(m)}});
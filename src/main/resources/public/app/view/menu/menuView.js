define("jquery underscore app/manager/sceneManager app/utils/utils app/utils/popupUtils app/utils/kongregateUtils app/data/textes app/utils/mediatheque app/manager/saveManager text!app/template/menu/menu.html app/view/game/gameView app/view/menu/optionView app/view/menu/traductionsView app/view/menu/creditView".split(" "),function(a,f,e,g,h,k,d,l,m,n,p,q,r,s){return function(){this.init=function(){this.el=a("#app");a(".text#loading").html(d.get("chargement"));this.scene=new e(this);this.mediatheque=
new l;this.mediatheque.load("music/menu.mp3");this.mediatheque.loadAll();this.kongregateUtils=new k(d);this.saveManager=new m(this.kongregateUtils,d);this.Textes=d;this.Textes.setSaveManager(this.saveManager);this.Textes.loadLanguage();var b=this;-1<window.location.href.indexOf("kongregate")?(console.log("kongregate Load"),this.kongregateUtils.load(function(){b.render()})):(console.log("Pas sur kongregate !"),this.render())};this.render=function(){this.Textes.loadLanguage();f.templateSettings.variable=
"data";var b=f.template(n),e=this.saveManager.checkSave();this.el.html(b({text:d,saveExists:e}));this.kongregateUtils.render();this.makeEvents();var c=this;a(".app-container").removeClass("bebel");this.scene.resize();setTimeout(function(){a(".text#loading").fadeOut("slow");a(".text#starting").fadeIn("slow");a(".preload").empty();c.scene.resize();c.optionView=new q(c);c.creditView=new s(c);c.traductionsView=new r(c)},1E3)};this.refreshTextes=function(){this.el.find("bouton#new span").html(this.Textes.get("newGame"));
this.el.find("bouton#load span").html(this.Textes.get("continue"));this.el.find("bouton#didacticiel span").html(this.Textes.get("didacticiel"));this.el.find("bouton#option span").html(this.Textes.get("options"));this.el.find("bouton#traductions span").html(this.Textes.get("traductions"));this.el.find("bouton#credit span").html(this.Textes.get("credits"));this.optionView.refreshTextes();this.creditView.refreshTextes();this.traductionsView.refreshTextes()};this.onResize=function(){this.traductionsView.onResize()};
this.makeEvents=function(){var b=this;a("#new").click(function(){b.saveManager.checkSave()?(b.el.find("carnet boutons").fadeOut(),h.confirm(d,"eraseSave",function(){b.saveManager.eraseSave();b.loadGame()},function(){b.el.find("carnet boutons").fadeIn()},"continuerButton","cancelButton")):(b.saveManager.eraseSave(),b.loadGame())});a("#didacticiel").click(function(){b.loadGame(!0)});a("#load").click(function(){b.saveManager.loadSave();b.loadGame()});a("#option").click(function(){b.optionView.show()});
a("#traductions").click(function(){b.traductionsView.showFiles()});a("#credit").click(function(){b.creditView.show()});a("#login").click(function(){b.kongregateUtils.login()});a("bouton").hover(function(){a(this).find("case").addClass("coche")},function(){a(this).find("case").removeClass("coche")});a(".page.bebel").click(function(){b.mediatheque.stopAllMusic();setTimeout(function(){b.mediatheque.play("music/menu.mp3");a(".page.bebel").fadeOut("slow",function(){a(".page.bebel").remove();a(".text#loading").remove()})},
100)});a("fullscreen").click(function(){g.fullscreen()?a("fullscreen").removeClass("exit"):a("fullscreen").addClass("exit");b.onResize()});a("body").contextmenu(function(){return!1})};this.loadGame=function(a){new p(this,a)};this.init()}});
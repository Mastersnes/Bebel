define(["jquery","underscore","app/utils/viewUtils","app/utils/utils"],function(h,n,k,d){return function(l,m){this.init=function(a,b){this.el="";this.parent=a;this.Textes=a.Textes;this.playerData=a.playerData;this.type=b};this.initGame=function(){this.gold=2E3;this.clearMain();this.renderActions()};this.reset=function(){this.clearMain();this.firstMise=!0;"garde"==this.type&&(this.ia=-1<this.parent.tuto?1:d.rand(0,5))};this.clearMain=function(){this.main={"carte-0":null,"carte-1":null,"carte-2":null,
"carte-3":null,"carte-4":null}};this.getGold=function(){return"garde"==this.type||-1<this.parent.tuto?this.gold:this.playerData.get("gold")};this.addGold=function(a){"garde"==this.type||-1<this.parent.tuto?this.gold+=a:this.playerData.addGold(a)};this.getFirstAction=function(){for(var a in this.main){var b=this.main[a];if(b&&-1<b.name.indexOf("-action"))return b}};this.getCarte=function(a){return this.main[a]};this.getPoints=function(){var a=0,b;for(b in this.main){var c=this.main[b];c&&("pomme"==
c.name&&a++,"fromage"==c.name&&(a+=2),"potionSante"==c.name&&(a+=3))}return a};this.getRandCarte=function(){for(var a in this.main){var b=this.main[a];if(b&&d.rand(0,2))return b}return this.getFirstCarte()};this.getFirstCarte=function(){for(var a in this.main){var b=this.main[a];if(b)return b}};this.length=function(){var a=0,b;for(b in this.main)this.main[b]&&a++;return a};this.add=function(a,b,c){var f=!1,e;for(e in this.main)if(!this.main[e]){this.main[e]={id:e,name:a,visible:b};f=!0;break}var g=
this;d.then(function(){g.renderActions();c&&c()},300);return f};this.remove=function(a,b){for(var c in this.main)if(c==a.id){this.main[c]=null;break}var f=this;d.then(function(){f.renderActions();b&&b()},300)};this.miser=function(){var a=this.type,b=this.parent.mise;this.firstMise&&(b=this.parent.startMise);if(this.getGold()<b)return this.parent.checkMise(),!1;if("garde"==a){if(this.parent.isPlayerTurn)return!1;this.parent.alert("garde-mise",null,[b])}else{if(!this.parent.isPlayerTurn||this.parent.lockMise)return!1;
this.parent.alert("player-turn-2")}this.addGold(-b);this.parent.total+=b;this.parent.mise=this.firstMise?1:this.parent.mise+2;this.firstMise=!1;this.hasMise=!0;this.parent.checkMise();return!0};this.piocher=function(){if("garde"!=this.type){if(this.alreadyPioche)return console.log("Erreur, vous avez deja pioché"),!1;if(!this.hasMise)return console.log("Erreur, Impossible de piocher, le joueur n'a pas misé."),!1;if(!this.parent.isPlayerTurn)return console.log("Erreur, Impossible de piocher, ce n'est pas votre tour"),
!1}this.alreadyPioche=!0;var a=this,b;b=3==this.parent.tuto?"garde"!=this.type?this.parent.pioche.indexOf("pokgard-voir-action"):this.parent.pioche.indexOf("pomme"):d.rand(0,this.parent.pioche.length);b=this.parent.pioche.splice(b,1)[0];if(5>this.length())this.add(b);else return!1;this.parent.checkGameOver()||d.then(function(){"garde"==a.type?a.parent.playerTurn():a.parent.gardeTurn()});return!0};this.use=function(a,b,c){if(!a||-1==a.name.indexOf("-action")||a.used)return c&&c(),!1;a.used=!0;var f=
this,e=this.parent.garde;if("garde"==this.type){var g=this.Textes.get(a.name);this.parent.alert("garde-use",null,[g]);e=this.parent.player;b=this.parent.player.getRandCarte()}else if(!b)return c||c(),!1;this.remove(a,function(){d.then(function(){switch(a.name){case "pokgard-voir-action":if(!f.parent.isPlayerTurn){c&&c();break}b.visible=!0;e.renderActions();c&&c();break;case "pokgard-voler-action":e.remove(b);f.add(b.name,!0,function(){c&&c()});break;case "pokgard-detruire-action":e.remove(b,function(){c&&
c()});break;default:c&&c()}},300)});this.parent.closePending()};this.recursiveUse=function(a,b){var c=this;5>a?this.use(this.main["carte-"+a],null,function(){c.recursiveUse(a+1,b)}):d.then(b)};this.montre=function(){for(var a in this.main){var b=this.main[a];b&&(b.visible=!0)}var c=this;d.then(function(){c.renderActions()},300)};this.renderActions=function(){var a=this.parent.el,b=a.find("#playerActions"),c=this.main,f="150%";"garde"==this.type&&(b=a.find("#gardeActions"),f="-150%");b.find("action").attr("vide",
"vide");for(var e in c)if(c[e]){var a=c[e],g=b.find("action[id\x3d'"+e+"']");h(g).removeAttr("vide");"garde"!=this.type||a.visible?(h(g).find("name").html(this.Textes.get(a.name)),h(g).attr("type",a.name)):(h(g).find("name").html("???"),h(g).attr("type",""));h(g).animate({top:"0"},300)}d.then(function(){b.find("action[vide]").each(function(a,b){h(b).animate({top:f},300,function(){h(b).find("name").html("");h(b).attr("type","")})});k.verticalCenter()},100);"garde"!=this.type&&this.parent.refreshScore()};
this.manageIA=function(){var a=this;switch(this.ia){case 0:d.rand(0,2)&&this.miser();case 1:this.piocher();break;case 2:d.rand(0,2)&&this.miser();case 3:this.use(this.getFirstAction(),null,function(){a.piocher()});break;case 4:d.rand(0,2)&&this.miser();case 5:this.recursiveUse(0,function(){a.piocher()})}};this.init(l,m)}});
define(["jquery","underscore","app/utils/utils","app/utils/popupUtils"],function(k,l,c,e){return function(g,h){this.init=function(a,b){this.el="";this.kongregateUtils=a;this.Textes=b;this.initSaveData()};this.initSaveData=function(){this.loaded=!1;this.saveData={player:{life:{current:150,max:150},mana:{current:0,max:0},unlockMana:!1,buff:null,debuff:null,xp:0,level:0,gold:0,attaque:1,defense:0,equipment:{arme:["poing"],currentArme:"poing",bouclier:["bras"],currentBouclier:"bras",magie:[],conso:[],
clef:[],ifObj:[]},lieu:"tuto-start",savesData:{},currentQuest:{name:null,step:0},quetesComplete:[]},success:{successComplete:[]}};this.gameOptions=(this.gameOptions=window.localStorage.getItem(c.name+"Options"))?JSON.parse(this.gameOptions):{selectAuto:!0,cibleUnique:!1};this.traductions=(this.traductions=window.localStorage.getItem(c.name+"Traductions"))?JSON.parse(this.traductions):{modified:{},toSend:{}}};this.setOption=function(a,b){this.gameOptions[a]=b;window.localStorage.setItem(c.name+"Options",
JSON.stringify(this.gameOptions))};this.getOption=function(a){return this.gameOptions[a]};this.options=function(){return this.gameOptions};this.deleteTrad=function(a,b){this.traductions.modified[a]&&this.traductions.modified[a][b]&&delete this.traductions.modified[a][b];this.traductions.toSend[a]&&this.traductions.toSend[a][b]&&delete this.traductions.toSend[a][b];this.nettoyage(this.traductions.modified);this.nettoyage(this.traductions.toSend);window.localStorage.setItem(c.name+"Traductions",JSON.stringify(this.traductions))};
this.nettoyage=function(a){for(var b in a)a[b].fr||a[b].en||a[b].eo||delete a[b]};this.addTrad=function(a,b,f,d){this.traductions.modified[a]||(this.traductions.modified[a]={});this.traductions.modified[a][b]=f;d||(this.traductions.toSend[a]||(this.traductions.toSend[a]={}),this.traductions.toSend[a][b]=f);window.localStorage.setItem(c.name+"Traductions",JSON.stringify(this.traductions))};this.myTrad=function(a,b){var c=this.traductions.modified;return c[a]?c[a][b]:null};this.sendTrad=function(a){var b=
this;a=c.encode(JSON.stringify(this.traductions.toSend));a={username:"",secretPass:c.hash("Samhain4842"),newTrad:a};c.load("https://bebel-server.herokuapp.com/samhain/sendTrad",a,function(a,d){console.log("Succes de l'envoi de la nouvelle traduction avec le status",d);b.traductions.toSend={};window.localStorage.setItem(c.name+"Traductions",JSON.stringify(b.traductions));e.alert(b.Textes,"traduction-success","continue")},"POST",!1,function(a){console.log("Erreur !!!",a);e.alert(b.Textes,"traduction-error",
"continue")})};this.getSave=function(){return this.loaded?this.saveData:null};this.checkSave=function(){this.saveSession=window.localStorage.getItem(c.name);if(!this.saveSession){var a=this.kongregateUtils.username;if(this.kongregateUtils.isLoad&&a){var b=this,a={username:a,secretPass:c.hash("Samhain4842"+a)};c.load("https://bebel-server.herokuapp.com/samhain/getSave",a,function(a,c){console.log("Succes de la recuperation de la sauvegarde",a,"avec le status",c);b.saveSession=a.data})}}return null!=
this.saveSession};this.loadSave=function(){this.saveSession&&(this.loaded=!0,this.saveData=JSON.parse(c.decode(this.saveSession)))};this.saveInSession=function(){var a=c.encode(JSON.stringify(this.saveData));window.localStorage.setItem(c.name,a)};this.saveInCloud=function(){var a=c.encode(JSON.stringify(this.saveData)),b=this.kongregateUtils.username;this.kongregateUtils.isLoad&&b&&(a={username:b,secretPass:c.hash("Samhain4842"+b),data:a},c.load("https://bebel-server.herokuapp.com/samhain/save",a,
null,"POST",!0))};this.eraseSave=function(){this.initSaveData();window.localStorage.removeItem(c.name)};this.save=function(a,b){this.saveData[a]=b};this.load=function(a){return this.saveData[a]};this.init(g,h)}});
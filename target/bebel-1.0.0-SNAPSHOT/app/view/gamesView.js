define(["jquery","underscore","app/utils/viewUtils","text!app/template/games.html"],function(a,b,c,d){return function(e){this.init=function(f){this.el=a("#games");this.parent=f;this.render()};this.render=function(){b.templateSettings.variable="data";var a=b.template(d);this.el.html(a({}));c.verticalCenter();this.checkEvents()};this.checkEvents=function(){a("#retour").click(function(){a(".flash-game embed").attr("src","");a(".flash-game").hide();a(".game-list").show()});a("#bul").click(function(){alert("En construction")});
a("#samhain").click(function(){window.open("https://les-jeux-de-bebel.itch.io/samhain","_blank")});a("#heritage").click(function(){a(".flash-game embed").attr("src","app/swf/heritage.swf");a(".flash-game").show();a(".game-list").hide()});a("#broc-and-house").click(function(){console.log("here");a(".flash-game embed").attr("src","app/swf/broc-and-house.swf");a(".flash-game").show();a(".game-list").hide()});a("#pieceOfCake").click(function(){window.open("https://les-jeux-de-bebel.itch.io/piece-of-cake",
"_blank")});a("#slimer").click(function(){window.open("https://les-jeux-de-bebel.itch.io/slimer","_blank")})};this.init(e)}});
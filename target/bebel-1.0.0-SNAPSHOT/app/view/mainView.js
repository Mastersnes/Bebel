define("jquery underscore app/utils/utils app/utils/messageUtils text!app/template/main.html app/model/checkMailModel app/view/gamesView".split(" "),function(d,b,e,c,f,g,h){return function(){this.init=function(){this.el=d("#app");this.render();"check"==e.getUrlParameter("origin")&&this.checkMail()};this.render=function(){b.templateSettings.variable="data";var a=b.template(f);this.el.html(a({}));this.gamesView=new h(this)};this.checkMail=function(){(new g).send(function(a){0!=a.codeRetour?c.show(a.message,
"danger"):c.show(a.message,"success")})};this.init()}});
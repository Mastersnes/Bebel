define(["app/data/textes/stories/suite/ville-textes","app/data/textes/stories/suite/bandits-textes"],function(f,g){var c={};return{name:function(){return"Suite.js"},children:function(){return[f,g]},get:function(b){var a=c[b],d=this.children(),e;for(e in d)a||(a=d[e].get(b));return a},list:function(){var b=[],a;for(a in c)b.push(a);return b}}});
define(["app/data/textes/stories/suite/bandits/porte-textes","app/data/textes/stories/suite/bandits/village-textes","app/data/textes/stories/suite/bandits/arene-textes"],function(f,g,h){var c={};return{name:function(){return"Suite Bandits.js"},children:function(){return[f,g,h]},get:function(b){var a=c[b],d=this.children(),e;for(e in d)a||(a=d[e].get(b));return a},list:function(){var b=[],a;for(a in c)b.push(a);return b}}});
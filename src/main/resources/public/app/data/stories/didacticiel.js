define(["app/data/stories/didacticiel/debut","app/data/stories/didacticiel/main","app/data/stories/didacticiel/combat"],function(c,d,e){var f={};return{get:function(b){var a=f[b];a||(a=c.get(b));a||(a=d.get(b));a||(a=e.get(b));return a}}});
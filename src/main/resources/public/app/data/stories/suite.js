define(["app/data/stories/suite/ville","app/data/stories/suite/bandits"],function(c,d){var e={};return{get:function(b){var a=e[b];a||(a=c.get(b));a||(a=d.get(b));return a}}});
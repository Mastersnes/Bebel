define(["jquery","underscore","app/utils/utils","app/data/etats"],function(h,k,e,f){return function(g){this.init=function(a){this.el="";this.parent=a;this.Textes=a.Textes;this.saveManager=a.saveManager;this.mediatheque=a.mediatheque;this.data=a.data};this.check=function(a,c){var b=f.get(a);b?(b.level=this.data.level,b.offensif?c.etatsManager.addEtat(b,c):this.addEtat(b,c)):console.log("Erreur - l'etat n'existe pas",a)};this.addEtat=function(a,c){if(a)if(a.duree=e.rand(a.duree[0],a.duree[1],!0)+a.level,
a.current=a.duree,a.offensif){var b=this.data.debuff;b&&b.type==a.type&&(a.duree+=b.current);b=c.get("attaque");Array.isArray(b)?(a.degats[0]+=b[0],a.degats[1]+=b[1]):(a.degats[0]+=b,a.degats[1]+=b);this.data.debuff=a}else(b=this.data.buff)&&b.type==a.type&&(a.duree+=b.current),this.data.buff=a};this.infligeEtats=function(a){var c=this;this.infligeEtat("debuff",function(){c.infligeEtat("buff",a)})};this.infligeEtat=function(a,c){var b=this.data[a];if(!b)return c?c():null;b.current--;if(b.degats){var d=
e.rand(b.degats[0],b.degats[1],!0);d&&this.parent.addLife(-d,b.element)}b.vie&&(d=e.rand(b.vie[0],b.vie[1],!0))&&this.parent.addPercentLife(d,b.element);0>=b.current&&(this.data[a]=null);c&&c()};this.init(g)}});
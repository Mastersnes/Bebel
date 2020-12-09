define(["jquery","app/utils/utils"],function(l,g){var h={arme:{poing:{name:"poing",texte:"poing-texte",degats:[0,0],sound:"punch",anim:"poing",price:0},baton:{name:"baton",texte:"baton-texte",degats:[1,1],sound:"baton",anim:"baton",price:50},dague:{name:"dague",texte:"dague-texte",degats:[1,2],sound:"dagguer",anim:"dague",price:100},epee:{name:"epee",texte:"epee-texte",degats:[1,3],sound:"sword",anim:"epee",price:200},crocRoi:{name:"crocRoi",texte:"crocRoi-texte",degats:[1,2],lifeSteal:[50,100],sound:"croc",
anim:"croc",price:0}},bouclier:{bras:{name:"bras",texte:"bras-texte",defense:[0,1],sound:"block",anim:null,price:0},bouclierBois:{name:"bouclierBois",texte:"bouclierBois-texte",defense:[0,3],sound:"woodblock",anim:null,price:100},bouclierFer:{name:"bouclierFer",texte:"bouclierFer-texte",defense:[0,5],sound:"ironblock",anim:null,price:200}},conso:{potionSante:{name:"potionSante",texte:"potionSante-texte",vie:[100,100],sound:"drink",anim:null,price:100,offensif:!1},potionMana:{name:"potionMana",texte:"potionMana-texte",
mana:[100,100],sound:"drink",anim:null,price:100,offensif:!1},antidote:{name:"antidote",texte:"antidote-texte",commentaire:"antidote-commentaire",sound:"drink",anim:null,price:100,offensif:!1,action:function(a){var b=a.get("debuff");b&&"poison"==b.element&&(a.data.debuff=null)}},fromage:{name:"fromage",texte:"fromage-texte",vie:[20,30],effet:["satiete"],sound:"eat",anim:null,price:50,offensif:!1},pomme:{name:"pomme",texte:"pomme-texte",vie:[10,20],effet:["satiete"],sound:"eat",anim:null,price:30,
offensif:!1},venin:{name:"venin",texte:"venin-texte",degats:[1,3],effet:["poison"],sound:"acide",anim:"venin",multicible:!0,price:100,offensif:!0},elixir:{name:"elixir",texte:"elixir-texte",vie:[100,100],mana:[100,100],sound:"drinkElixir",anim:null,price:300,offensif:!1}},magie:{bouleFeu:{name:"bouleFeu",texte:"bouleFeu-texte",degats:[3,5],effet:["brulure"],manaCost:1,sound:"bouleFeu",anim:"bouleFeu",price:300,offensif:!0,element:"feu"},soin:{name:"soin",texte:"soin-texte",vie:[20,40],manaCost:1,
sound:"soin",anim:null,price:300,offensif:!1},guerison:{name:"guerison",texte:"guerison-texte",manaCost:1,sound:"soin",anim:null,price:400,offensif:!1,action:function(a){a.data.debuff=null}},morsure:{name:"morsure",texte:"morsure-texte",degats:[1,2],lifeSteal:[50,100],effet:["poison"],manaCost:1,sound:null,anim:null,price:null,offensif:!0,element:"poison"},araneisme:{name:"araneisme",texte:"araneisme-texte",degats:[3,5],lifeSteal:[50,100],effet:["poison-2"],manaCost:1,sound:null,anim:null,price:null,
offensif:!0,element:"poison"},voler:{name:"voler",texte:"voler-texte",manaCost:1,sound:null,anim:null,price:null,offensif:!0,action:function(a,b){var c=g.rand(10*(a.level+1),20*(a.level+1));a.isMonster?a.steal("gold",b,c,0):a.addGold(c)},element:"gold"},doubleAttaque:{name:"doubleAttaque",texte:"doubleAttaque-texte",manaCost:1,sound:null,anim:null,price:null,offensif:!0,action:function(a,b){a.attaque(b,!0);a.attaque(b,!0)}},volDeVie:{name:"volDeVie",texte:"volDeVie-texte",lifeSteal:[10,30],manaCost:1,
sound:null,anim:null,price:null,offensif:!0},volDeMana:{name:"volDeMana",texte:"volDeMana-texte",manaSteal:[10,30],manaCost:1,sound:null,anim:null,price:null,offensif:!0},taillade:{name:"taillade",texte:"taillade-texte",degats:[2,5],effet:["saignement"],manaCost:1,sound:null,anim:null,price:null,offensif:!0},pondreAraignee:{name:"pondreAraignee",texte:"pondreAraignee-texte",manaCost:1,sound:null,anim:null,price:null,offensif:!0,contrainte:function(a,b,c){return 3>c.aliveMonsters().length},action:function(a,
b,c){c.addMonstre("araignee",3)}},invoqueGoule:{name:"invoqueGoule",texte:"invoqueGoule-texte",manaCost:1,sound:null,anim:null,price:null,offensif:!0,action:function(a,b,c){c.addMonstre("goule",3)}},invoqueGrosseGoule:{name:"invoqueGrosseGoule",texte:"invoqueGrosseGoule-texte",manaCost:1,sound:null,anim:null,price:null,offensif:!0,action:function(a,b,c){c.addMonstre("grosseGoule",3)}},cannibalisme:{name:"cannibalisme",texte:"cannibalisme-texte",degats:[2,4],lifeSteal:[50,100],manaCost:1,sound:null,
anim:null,price:null,offensif:!0}},clef:{bougie:{name:"bougie",texte:"bougie-texte"},clefS2:{name:"clefS2",texte:"clefS2-texte"},"anneau-bandit":{name:"anneau-bandit",texte:"anneau-bandit-texte"},poulet:{name:"poulet",texte:"poulet-texte"}},ifObj:"helpRoi helpRoi2 necroS1Clean hadClef S1ViaS0 S3ViaS0 viaS1 viaS3 parchoLu fouilleCercueil torcheAllumeG torcheExploseG torcheAllumeD torcheExploseD S2Ouvert S2Clean S4Clean S5Clean".split(" ")};return{get:function(a,b){return b?this.getByType(a,b):this.getAll(a)},
getAll:function(a){var b=this.getByType("arme",a);b||(b=this.getByType("bouclier",a));b||(b=this.getByType("conso",a));b||(b=this.getByType("magie",a));b||(b=this.getByType("clef",a));b||(b=a);return b},getByType:function(a,b){var c=h[a],d=null;c&&c[b]&&(d=g.clone(c[b]),"ifObj"!=a&&(d.type=a));return d},list:function(a,b,c){var d=this.listByType("arme",a,b,c),d=d.concat(this.listByType("bouclier",a,b,c)),d=d.concat(this.listByType("conso",a,b,c));return d=d.concat(this.listByType("magie",a,b,c))},
listByType:function(a,b,c,d){var e=[];if(Array.isArray(b))for(var k in b){var f=this.listByType(a,b[k],c,d),e=e.concat(f);if(d&&0<e.length)break}else for(f in h[a])if(0===g.normalize(c.get(f)).indexOf(b,0)&&e.push(f),d&&0<e.length)break;return e}}});
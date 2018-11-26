// npm install firebase-admin
const admin = require('firebase-admin');
var serviceAccount = require('./codex-bruxellensis-firebase-adminsdk-z44yg-1d2016c8eb.json'); // download key from google cloud

admin.initializeApp({
  credential: admin.credential.cert(serviceAccount)
});

var db = admin.firestore();

const song = {
                   "title": "Waar Het Hart Van Vol Is, Loopt De Mond Van Over",
                   "bgInfo": "T: Thomas Schoenmakers, Andrew Crosby, William Schoenmakers,\nLodewijk Janssen, Benjamin Van de Pol\nM: ‘Misery’, Greenday\nLWK, Winnend lied op het ‘25e Vrijzinnig Zangfeest van Vlaanderen’, 2010",
                   "lyrics": "Mijn liefste schat, de dingen die je graag doet\nals knuff’len, samen dansen op TD,\nsteeds zeggen dat ik je graag zie,\nomgaan met je jaloezie;\nMaar eigenlijk boeit het mij niet.\n\nWant ik doe graag, graag, graag\nwat je haat, haat, haat,\nniet omdat het leuker is...\nMaar ik doe graag, graag, graag\nwat je haat, haat, haat,\nal is het maar omdat je ’t niet tof vindt!\nVerwend kutkind!\n\nEn als ik straalbezopen in je bed pis\ndan denk je dat ik niet weet wat ik doe.\nWeet: ik ben nog vigilant,\n’k doe gewoon graag ambetant\nJou zien kuisen is plezant!\n\nIk vind schat dat we eeuwig moeten trouw zijn,\nmaar jij dan toch wel net iets meer dan mij.\nZuigt een ander aan mijn piet,\n’t is niet da ’k ervan geniet.\nIk heb graag jouw verdriet.\n\n\t\trefrein\n\nWanneer ik met mijn maten aan de toog hang,\nhebben we gespreksonderwerpen zat.\nToch gaat het over je ravijn,\nkont te groot, tetten te klein.\nJou kleineren is zo fijn.\n\nSchat, dit geheim moet ik je nog vertellen:\nkut of kont voor mij is dat één pot nat.\nToch dring ik altijd zo hard aan\nuit verlangen naar je traan.\nNu is uranus naar de maan.\n\nWant ik neuk je graag, graag, graag\nin je aars, aars, aars,\nniet omdat het strakker is...\nMaar ik neuk je graag, graag, graag\nin je aars, aars, aars,\nal is het maar omdat je ’t niet tof vindt!\nVerwend kutkind!\n\n\t\tMoraal:\n\nJe denkt misschien dat dit lied niet ruimdenkend is,\nmaar onze allerliefste Théodore\nkreeg ook al in zijnen tijd\nvan zijn wijf het vliegend schijt.\nHip hip hoera vrijzinnigheid!",
                   "associationName": "",
                   "associationInfo": "",
                   "battleCryName": "",
                   "battleCryInfo": "",
                   "battleCry": "",
                   "page": 245,
                   "category": "DUTCH"
                 };

// Update exisiting
var updateDoc = db.collection('songs')
    .doc('jqgOfsQPuzFK30RfOiZd')
    .set(song);

// Add a new document with a generated id.
/*var addDoc = db.collection('songs').add(song).then(ref => {
  console.log('Added song with ID: ', ref.id);
});*/

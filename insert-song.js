// npm install firebase-admin
const admin = require('firebase-admin');
var serviceAccount = require('JSONKEY'); // download key from google cloud

admin.initializeApp({
  credential: admin.credential.cert(serviceAccount)
});

var db = admin.firestore();

const song = {
   "title": "Heute Ist Heut",
   "bgInfo": "T: Rudolf Baumbach, 1882\nM: V.E. Becker, 1885\nUit ‘Mon viscèere a quelque chose’, revue van de medische studentenkring te Luik, 18 februari 1927.",
   "lyrics": "Was die Welt morgen bringt,\nOb sie mir Sorgen bringt,\nLeid oder Freud?\nKomme was kommen mag,\nSonnenschein, Wetterschlag,\nMorgen ist auch ein Tag\nHeute ist heut!\nWenn’s dem Geschick gefällt,\nSind wir in alle Welt\nMorgen zerstreut!\nD’rum lasst uns lustig sein!\nWirt, roll das Fass herein!\nMädel; schenk ein; schenk ein!\nHeute ist heut!\nOb ihren Rosenmund\nMorgen schön Hildegund\nAnderen beut\nDanach ich nimmer frag,\nDas scha?t mir keine Plag,\nWenn sie mich heut nur mag\nHeute ist heut!\n\nKlingklang, stoßt an und singt!\nMorgen vielleicht erklingt\nSterbegeläut!\nWer weiß, ob nicht die Welt\nMorgen in Schutt zerfällt\nWenn sie nur heut noch hält\nHeute ist heut!",
   "associationName": "",
   "associationInfo": "",
   "battleCryName": "",
   "battleCryInfo": "",
   "battleCry": "",
   "page": 433,
   "category": "GERMAN"
 };

// Add a new document with a generated id.
var addDoc = db.collection('songs').add(song).then(ref => {
  console.log('Added song with ID: ', ref.id);
});
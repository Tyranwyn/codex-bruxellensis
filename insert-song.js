// npm install firebase-admin
const admin = require('firebase-admin');
var serviceAccount = require('./codex-bruxellensis-firebase-adminsdk-z44yg-1d2016c8eb.json'); // download key from google cloud

admin.initializeApp({
  credential: admin.credential.cert(serviceAccount)
});

var db = admin.firestore();

const song = {
                   "title": "Tsjechisch Drinklied",
                   "bgInfo": "Dr. D. Devos",
                   "lyrics": "Drink uit dan, broeder, drink!\nDrink uit tot op den grond\nWant nooit zien w’ons weerom\nVoor ’t volle jaar is rond.\n\nEn daarom drink maar, drink maar, drink maar,\nZolang de beker ons nog wenkt,\nEn daarom drink maar, drink maar, drink maar,\nZolang een druppel wijn nog blinkt:\nEn daarom drink maar, drink maar, drink maar,\nEer we malkander ’t afscheid bi’en,\nEn daarom drink maar, drink maar, drink maar,\nDrink op het vrolijk wederzien!",
                   "associationName": "",
                   "associationInfo": "",
                   "battleCryName": "",
                   "battleCryInfo": "",
                   "battleCry": "",
                   "page": 96,
                   "category": "OFFICIAL"
                 };

// Update exisiting
var updateDoc = db.collection('songs')
    .doc('xmPLHAHmsbq6mkVqndJn')
    .set(song);

// Add a new document with a generated id.
/*var addDoc = db.collection('songs').add(song).then(ref => {
  console.log('Added song with ID: ', ref.id);
});*/

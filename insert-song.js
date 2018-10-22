// npm install firebase-admin
const admin = require('firebase-admin');
var serviceAccount = require('./codex-bruxellensis-5616053ee25e.json'); // download key from google cloud

admin.initializeApp({
  credential: admin.credential.cert(serviceAccount)
});

var db = admin.firestore();

const song = {
                   "title": "Ruiterslied",
                   "bgInfo": "T: Georg Herwegh, Burschenschaft Patrioten Tübingen, 1841\nM: Justus Wilhelm Lyra, Burschenschaft Knorschia Bonn, 1843",
                   "lyrics": "Dit lied wordt door de laatstejaars gezongen. Voor ze beginnen worden de glazen gevuld.\n\nDe bange nacht is weeral om,\nWe rijden stil, we rijden stom,\nWe rijden ten verderve!\nHoe koud waait toch de morgenwind!\nWeerdin, nu nog een glas gezwind\nVoor ’t sterven.\nHoe staat het jonge gras nu groen,\nMaar bloeden zal het morgen doen,\nMijn eigen bloed zal ’t verven\nDe eerste slok, met ’t zweerd in hand,\nGedronken voor het vaderland\nVoor ’t sterven.\nHier wordt een eerste slok gedronken\nDe tweede slok van d’edele wijn,\nZal voor de heilige vrijheid zijn,\nVoor vrijheid, have en erve;\nHier wordt de tweede slok gedronken\nJ In Memoriam Bertholet, La Curée, tijdschrift van CPL, novem-\nber 1956. Ter gelegenheid van de zwanezang van één van de comitards\nvan CPL.\n\nDe rest zij nog een huldeblijk,\nDe laatst aan ’t oud Romeinse rijk\nVoor ’t sterven.\nHier wordt het glas geledigd\nVoor ’t liefken, maar mijn glas is uit,\nDe spere blinkt, de kogel fluit,\nDraag aan mijn kind de scherven,\nHier wordt het glas kapotgegooid\nVooruit nu naar de laatste slag.\nO, ruiterslust in vroege dag.\nEn sterven.",
                   "associationName": "",
                   "associationInfo": "",
                   "battleCryName": "",
                   "battleCryInfo": "",
                   "battleCry": "",
                   "page": 215,
                   "category": "DUTCH"
                 };

// Add a new document with a generated id.
var addDoc = db.collection('songs').add(song).then(ref => {
  console.log('Added song with ID: ', ref.id);
});
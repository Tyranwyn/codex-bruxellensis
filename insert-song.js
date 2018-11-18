// npm install firebase-admin
const admin = require('firebase-admin');
var serviceAccount = require('./codex-bruxellensis-firebase-adminsdk-z44yg-f1c5a31819.json'); // download key from google cloud

admin.initializeApp({
  credential: admin.credential.cert(serviceAccount)
});

var db = admin.firestore();

const song = {
                   "title": "La Fille De La Bouchère",
                   "bgInfo": "Gebaseerd op waargebeurde feiten;\nFestival de la chanson estudiantine 1998",
                   "lyrics": "Je me souviens de la fille de la bouchère,\nElle était vraiment conne comme sa mère,\nMais quand on avait bu assez de bière,\nOn lui mettait dans son derrière.\nSortant du Wapps en état plus potable,\nEnvie de niquer cette pétasse sur la table,\nTellement bourré ma vision très minable,\nJ’ai éclaté un pot de salade de crabe.\nEn douceur, en douceur, en douceur et profondeur.\nL’était chouette la fille de la bouchère,\n(Schwing, schwing, schwing)\nElle nous faisait bander comme des cerfs.\n\nAprès St V et beaucoup de chopines,\nJe voulais bien lui faire sucer ma pine,\nLa tête pressée trop près de sa poitrine,\nEt sur mon bout collait de la gélatine.\nLe soir d’un fût il m’fallait de la baise,\nEntr’les jambons je me sentais bien à l’aise,\nJe lui ai mis ma saucisse ardennaise,\nBien lubrifiée avec d’la dijonnaise.\nEn douceur, en douceur, j’ai tripoté sa petite fleur.\nRefrein\nElle s’était réfugiée dans le frigo,\nLa porte s’est fermée derrière mon dos,\nJe me suis fait lécher mon Calippo,\nPar une gigantesque tête de veau.\nJ’ai raconté toutes mes chaudes histoires,\nDe sorte que tout le Cercle y aille la voir,\nEt complètement bourré comme tous les soirs,\nOn sautait tout ce qu’y avait dans le comptoir.\nMoraal:\nLa morale de cette histoire est ferme,\nBoire et baiser ça tue le commerce.\nLa boucherie s’est fermée car à long terme,\nToute la viande y goûtait fort le sperme.\nEh encore, eh encore,\nAu Cercle Popo on est des porcs.\nRefrein ad libitum",
                   "associationName": "",
                   "associationInfo": "",
                   "battleCryName": "",
                   "battleCryInfo": "",
                   "battleCry": "",
                   "page": 331,
                   "category": "FRENCH"
                 };

// Update exisiting
/*var updateDoc = db.collection('songs')
    .doc('pwYsLjFesZkgw6yWrnCE')
    .set(song);*/

// Add a new document with a generated id.
var addDoc = db.collection('songs').add(song).then(ref => {
  console.log('Added song with ID: ', ref.id);
});

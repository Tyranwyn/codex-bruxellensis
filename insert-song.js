// npm install firebase-admin
const admin = require('firebase-admin');
var serviceAccount = require('./codex-bruxellensis-firebase-adminsdk-z44yg-c52182ac92.json'); // download key from google cloud

admin.initializeApp({
  credential: admin.credential.cert(serviceAccount)
});

var db = admin.firestore();

const song = {
                   "title": "La Bière",
                   "bgInfo": "T & M: Antoine Clesse (Bergen)\nWerd oorspronkelijk op een andere melodie gezongen.",
                   "lyrics": "Elle a vraiment d’une bière flamande\nL’air avenant, l’éclat et la douceur,\nJoyeux Wallons, elle nous a?riande\nEt le Faro trouve en elle une soeur.\nÀ plein verre, mes bons amis,\nEn la buvant, il faut chanter la bière,\nÀ plein verre, mes bons amis.\nIl faut chanter la bière du pays.\n\nVoyez là-bas la kermesse en délire:\nLes pots sont pleins, jouez ménétriers!\nQuels jeux bruyants et quels éclats de rire!\nCe sont encore les Flamands de Teniers!\nAux souverains, portant tout haut leurs plaintes,\nBourgeois jaloux des droits de la cité,\nNos francs aîeux, tout en vidant leur pinte,\nFondaient les arts avec la liberté.\nQuand leurs tribuns à l’attitude altière,\nFaisaient sonner le tocsin des be?rois,\nTous ces fumeurs, tous ces buveurs de Bière,\nSavaient combattre et mourir pour leurs droits.\nBelges, chantons, à ce refrein à boire,\nPeintres, guerriers qui nous illustrent tous,\nGéants couchés dans leur linceul de gloire,\nVont se lever, pour redire avec nous.\nSalut à toi, Bière limpide et blonde!\nJe tiens mon verre, et le bonheur en main,\nAh! J’en voudrais verser à tout le monde,\nPour le bonheur de tout le genre humain.",
                   "associationName": "",
                   "associationInfo": "",
                   "battleCryName": "",
                   "battleCryInfo": "",
                   "battleCry": "",
                   "page": 278,
                   "category": "FRENCH"
                 };

// Update exisiting
/*var updateDoc = db.collection('songs')
    .doc('rTZ3d34I8xGwA0A5wH2M')
    .set(song);*/

// Add a new document with a generated id.
var addDoc = db.collection('songs').add(song).then(ref => {
  console.log('Added song with ID: ', ref.id);
});
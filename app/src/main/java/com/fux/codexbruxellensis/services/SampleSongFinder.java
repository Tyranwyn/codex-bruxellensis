package com.fux.codexbruxellensis.services;

import com.fux.codexbruxellensis.model.AssociationSong;
import com.fux.codexbruxellensis.model.Category;
import com.fux.codexbruxellensis.model.Song;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;

@EBean
public class SampleSongFinder implements SongFinder {
    @Override
    public ArrayList<Song> findAll() {
        Song p1 = new AssociationSong("Technica", "T: Marc \"Zarre Gosseye\" en Gert Sanders", "<td>\n" +
                " <div class=\"kring\">\n" +
                "  Technica\n" +
                "Erasmushogeschool Brussel, Departement Industriële Wetenschappen en Technologie\n" +
                "Gesticht in 1979, uit de kringen Ad Fundum en Atomos\n" +
                "Kleuren: geel-zwart-geel\n" +
                " </div>\n" +
                " <div class=\"title\">\n" +
                "  het Technicalied\n" +
                " </div>\n" +
                " <div class=\"bginfo\">\n" +
                "  M: 'Het lied van hertog Jan'\n" +
                " </div>\n" +
                " <div class=\"verse\">\n" +
                "  Gezeten en getogen\n" +
                "Aan d'oude vaart, te Anderlecht\n" +
                "O club waar wij op bogen\n" +
                "Aan U zijn wij verknecht\n" +
                " </div>\n" +
                " <div class=\"chorus\">\n" +
                "  Vivat Technica, floreat cresceat, vivat Technica\n" +
                "O club waar wij op bogen\n" +
                "Aan U zijn wij verknecht\n" +
                " </div>\n" +
                " <div class=\"verse\">\n" +
                "  Gedenken wij de dagen,\n" +
                "van 't Chomé-Wijns, derde verdiep\n" +
                "Geen kaloot die zich waagde\n" +
                "In 't Ad Fundum gebied\n" +
                " </div>\n" +
                " <div class=\"verse\">\n" +
                "  Het Manneke kwam logeren\n" +
                "wij kennen hem nu, ne zeer bien vent\n" +
                "Wij gaven hem ook kleren\n" +
                "Nu is hij ook student\n" +
                " </div>\n" +
                " <div class=\"verse\">\n" +
                "  Ons moeder en ons vader\n" +
                "Waar wij zo trots, en fier op zijn\n" +
                "Zo zijn er geen twee ander\n" +
                "Voor hen zingen wij dit refrein\n" +
                " </div>\n" +
                " <div class=\"verse\">\n" +
                "  Ingenieurs en analisten,\n" +
                "Samen aktief, in leed en lief\n" +
                "We laten ons niet kisten\n" +
                "Schachten aanhoor ons lied\n" +
                " </div>\n" +
                "</td>\n");
        Song p2 = new Song("Ave Confrater", "\"  Dit lied wordt gezongen bij de overdracht van het praesesschap. Na de overhandiging van het praeseslint en de eedaflegging, gaan de oude en de nieuwe senior over tot de Ave Confrater.\\n\"", "<td>\n" +
                " <div class=\"title\">\n" +
                "  Ave Confrater\n" +
                " </div>\n" +
                " <div class=\"bginfo\">\n" +
                "  Zij staan naast elkaar, de rechterarm onderling gekruist en brengen het glas, ook met de rechterhand, aan de mond. Onder het zingen draaien ze om hun gemeenschappelijke verticale as.\n" +
                " </div>\n" +
                " <div class=\"songinfo\">\n" +
                "  Legende:\n" +
                " </div>\n" +
                " <div class=\"songinfo\">\n" +
                "  1 = aftredende senior\n" +
                " </div>\n" +
                " <div class=\"songinfo\">\n" +
                "  2 = nieuwe senior\n" +
                " </div>\n" +
                " <div class=\"verse\">\n" +
                "  1 Ave confrater\n" +
                " 2 'k Drink liever bier dan water\n" +
                " </div>\n" +
                " <div class=\"songinfo\">\n" +
                "  Nummer 2 begint te drinken bij 1, bij 7 heeft hij zijn glas ad fundum gedronken en zet het omgekeerd op zijn hoofd.\n" +
                "dverse 1 Drink dan op het kommando van 1, 2, 3, 4, 5, 6, 7\n" +
                " 2 Er is niets meer in mijn glas gebleven\n" +
                " 2 Ave confrater\n" +
                " 1 'k Drink liever bier dan water\n" +
                " </div>\n" +
                " <div class=\"songinfo\">\n" +
                "  Nummer 1 drinkt op dezelfde manier.\n" +
                "dverse 2 Drink dan op het kommando van 1, 2, 3, 4, 5, 6, 7\n" +
                " 1 Er is niets meer in mijn glas gebleven\n" +
                " 2 Flexamus genua\n" +
                " </div>\n" +
                " <div class=\"songinfo\">\n" +
                "  Beide commilitones maken een knieval.\n" +
                "dverse 1 Levate\n" +
                " </div>\n" +
                " <div class=\"songinfo\">\n" +
                "  Beide commilitones staan terug recht.\n" +
                "dverse 1+2 Ave confrater\n" +
                " </div>\n" +
                "</td>\n");

        ArrayList<Song> personList = new ArrayList<>();
        personList.add(p1);
        personList.add(p2);

        return personList;
    }
}

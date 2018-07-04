package com.fux.codexbruxellensis.model;

public class AssociationSong extends Song {

    private String associationName;
    private String associationInfo;
    private String battleCryName;
    private String battleCryInfo;
    private String battleCry;

    public AssociationSong() {}

    public AssociationSong(String title, String bgInfo, String lyrics) {
        super(title, bgInfo, lyrics);
    }

    public String getAssociationInfo() {
        return associationInfo;
    }

    public AssociationSong setAssociationInfo(String associationInfo) {
        this.associationInfo = associationInfo;
        return this;
    }

    public String getAssociationName() {
        return associationName;
    }

    public AssociationSong setAssociationName(String associationName) {
        this.associationName = associationName;
        return this;
    }

    public String getBattleCryName() {
        return battleCryName;
    }

    public AssociationSong setBattleCryName(String battleCryName) {
        this.battleCryName = battleCryName;
        return this;
    }

    public String getBattleCry() {
        return battleCry;
    }

    public AssociationSong setBattleCry(String battleCry) {
        this.battleCry = battleCry;
        return this;
    }

    public String getBattleCryInfo() {
        return battleCryInfo;
    }

    public AssociationSong setBattleCryInfo(String battleCryInfo) {
        this.battleCryInfo = battleCryInfo;
        return this;
    }
}

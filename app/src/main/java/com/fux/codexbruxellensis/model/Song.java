package com.fux.codexbruxellensis.model;

import java.io.Serializable;

public class Song implements Serializable {

    private Category category;
    private String title;
    private String bgInfo;
    private String lyrics;
    private String associationName;
    private String associationInfo;
    private String battleCryName;
    private String battleCryInfo;
    private String battleCry;

    private Integer page;
    private boolean removed;
    private boolean favorite;

    public Song() {}

    public Song(String title, String bgInfo, String lyrics) {
        this.title = title;
        this.bgInfo = bgInfo;
        this.lyrics = lyrics;
    }
    public String getTitle() {
        return title;
    }

    public Song setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getBgInfo() {
        return bgInfo;
    }

    public Song setBgInfo(String bgInfo) {
        this.bgInfo = bgInfo;
        return this;
    }

    public String getLyrics() {
        return lyrics;
    }

    public Song setLyrics(String lyrics) {
        this.lyrics = lyrics;
        return this;
    }

    public boolean isRemoved() {
        return removed;
    }

    public Song setRemoved(boolean removed) {
        this.removed = removed;
        return this;
    }

    public Integer getPage() {
        return page;
    }

    public Song setPage(Integer page) {
        this.page = page;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public Song setCategory(Category category) {
        this.category = category;
        return this;
    }

    public String getAssociationName() {
        return associationName;
    }

    public Song setAssociationName(String associationName) {
        this.associationName = associationName;
        return this;
    }

    public String getAssociationInfo() {
        return associationInfo;
    }

    public Song setAssociationInfo(String associationInfo) {
        this.associationInfo = associationInfo;
        return this;
    }

    public String getBattleCryName() {
        return battleCryName;
    }

    public Song setBattleCryName(String battleCryName) {
        this.battleCryName = battleCryName;
        return this;
    }

    public String getBattleCryInfo() {
        return battleCryInfo;
    }

    public Song setBattleCryInfo(String battleCryInfo) {
        this.battleCryInfo = battleCryInfo;
        return this;
    }

    public String getBattleCry() {
        return battleCry;
    }

    public Song setBattleCry(String battleCry) {
        this.battleCry = battleCry;
        return this;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}

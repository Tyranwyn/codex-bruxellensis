package com.fux.codexbruxellensis.model;

public class Song {

    private Category category;
    private String title;
    private String bginfo;
    private String lyrics;
    private boolean removed;

    public Song(Category category, String title, String bginfo, String lyrics, boolean removed) {
        this.category = category;
        this.title = title;
        this.bginfo = bginfo;
        this.lyrics = lyrics;
        this.removed = removed;
    }

    public Category getCategory() {
        return category;
    }

    public Song setCategory(Category category) {
        this.category = category;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Song setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getBginfo() {
        return bginfo;
    }

    public Song setBginfo(String bginfo) {
        this.bginfo = bginfo;
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
}

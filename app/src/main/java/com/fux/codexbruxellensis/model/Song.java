package com.fux.codexbruxellensis.model;

public class Song {

    private String title;
    private String bgInfo;
    private String lyrics;
    private boolean removed;
    private Integer page;

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
}

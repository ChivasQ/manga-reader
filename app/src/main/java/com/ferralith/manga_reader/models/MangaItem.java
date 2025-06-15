package com.ferralith.manga_reader.models;

public class MangaItem {
    public final String title;
    public final String coverUrl;

    public MangaItem(String title, String coverUrl) {
        this.title = title;
        this.coverUrl = coverUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getCoverUrl() {
        return coverUrl;
    }
}

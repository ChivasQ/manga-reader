package com.ferralith.manga_reader.api.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MangaListResponse {
    @SerializedName("data")
    private List<MangaItem> data;

    public List<MangaItem> getData() {
        return data;
    }
}
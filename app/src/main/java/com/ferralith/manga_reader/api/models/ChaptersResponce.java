package com.ferralith.manga_reader.api.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChaptersResponce {
    @SerializedName("data")
    private List<ChapterItem> data;

    public List<ChapterItem> getData() {
        return data;
    }
}

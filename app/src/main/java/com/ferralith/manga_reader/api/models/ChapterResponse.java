package com.ferralith.manga_reader.api.models;

import com.google.gson.annotations.SerializedName;

public class ChapterResponse {
    @SerializedName("data")
    public ChapterData data;
}
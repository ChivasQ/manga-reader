package com.ferralith.manga_reader.api.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChapterData {
    @SerializedName("id")
    public int id;

    @SerializedName("volume")
    public String volume;

    @SerializedName("number")
    public String number;

    @SerializedName("number_secondary")
    public String number_secondary;

    @SerializedName("name")
    public String name;

    @SerializedName("slug")
    public String slug;

    @SerializedName("pages")
    public List<ChapterPage> pages;
}

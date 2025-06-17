package com.ferralith.manga_reader.api.models;

import com.google.gson.annotations.SerializedName;

public class ChapterPage {
    @SerializedName("id")
    public int id;

    @SerializedName("image")
    public String image;

    @SerializedName("slug")
    public int slug;

    @SerializedName("url")
    public String url;

    @SerializedName("width")
    public int width;

    @SerializedName("height")
    public int height;

    @SerializedName("ratio")
    public String ratio;
}

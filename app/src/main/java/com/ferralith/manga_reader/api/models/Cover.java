package com.ferralith.manga_reader.api.models;

import com.google.gson.annotations.SerializedName;

public class Cover {
    @SerializedName("thumbnail")
    public String thumbnail;
    @SerializedName("default")
    public String default_;
}
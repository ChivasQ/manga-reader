package com.ferralith.manga_reader.api.models;

import android.os.Parcel;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class MangaItem {
    @SerializedName("id")
    public int id;
    @SerializedName("name")
    public String name;
    @SerializedName("rus_name")
    public String rusName;
    @SerializedName("cover")
    public Cover cover;
    @SerializedName("slug_url")
    public String slug_url;

    public String getSlug_url() {
        return slug_url;
    }

    public String getTitle() {
        return rusName;
    }

    public String getCoverUrl() {
        return cover.default_;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRusName() {
        return rusName;
    }

    public Cover getCover() {
        return cover;
    }
}

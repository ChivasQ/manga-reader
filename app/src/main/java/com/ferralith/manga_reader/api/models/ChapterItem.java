package com.ferralith.manga_reader.api.models;

import com.google.gson.annotations.SerializedName;

public class ChapterItem {
    @SerializedName("id")
    public int id;
    @SerializedName("index")
    public int index;
    @SerializedName("item_number")
    public int item_number;
    @SerializedName("volume")
    public String volume;
    @SerializedName("number")
    public String number;
    @SerializedName("number_secondary")
    public String number_secondary;
    @SerializedName("name")
    public String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getItem_number() {
        return item_number;
    }

    public void setItem_number(int item_number) {
        this.item_number = item_number;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber_secondary() {
        return number_secondary;
    }

    public void setNumber_secondary(String number_secondary) {
        this.number_secondary = number_secondary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

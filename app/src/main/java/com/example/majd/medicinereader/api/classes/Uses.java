package com.example.majd.medicinereader.api.classes;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Majd on 2018-01-27.
 */

public class Uses {

    @SerializedName("id")
    private String id;

    @SerializedName("medicine_id")
    private String medicine_id;

    @SerializedName("use_for")
    private String use_for;

    public String getId() {
        return id;
    }

    public String getMedicine_id() {
        return medicine_id;
    }

    public String getUse_for() {
        return use_for;
    }
}

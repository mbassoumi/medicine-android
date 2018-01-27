package com.example.majd.medicinereader.api.classes;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Majd on 2018-01-27.
 */

public class MedicineData {

    @SerializedName("medicine")
    private Medicine medicine;

    @SerializedName("sideEffects")
    private ArrayList<SideEffects> sideEffects;

    @SerializedName("uses")
    private ArrayList<Uses> uses;

    @SerializedName("warnings")
    private ArrayList<Warnings> warnings;



    public Medicine getMedicine() {
        return medicine;
    }

    public ArrayList<SideEffects> getSideEffects() {
        return sideEffects;
    }

    public ArrayList<Uses> getUses() {
        return uses;
    }

    public ArrayList<Warnings> getWarnings() {
        return warnings;
    }
}

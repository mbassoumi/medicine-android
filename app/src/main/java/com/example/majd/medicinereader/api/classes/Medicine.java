package com.example.majd.medicinereader.api.classes;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Majd on 2018-01-27.
 */

public class Medicine {


    @SerializedName("id")
    private String id;

    @SerializedName("barcode")
    private String barcode;

    @SerializedName("name")
    private String name;

    @SerializedName("use")
    private String use;

    @SerializedName("store")
    private String store;


    public String getBarcode() {
        return barcode;
    }

    public String getName() {
        return name;
    }

    public String getUse() {
        return use;
    }

    public String getStore() {
        return store;
    }
}

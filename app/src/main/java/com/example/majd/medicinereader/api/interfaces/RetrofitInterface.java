package com.example.majd.medicinereader.api.interfaces;

/**
 * Created by Mais
 */


import com.example.majd.medicinereader.api.classes.MedicineData;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by Mais
 */


public interface RetrofitInterface {
    // check if user register or not
 
    @GET("medicine/{barcode}")
    Call<MedicineData> getMedicineData(@Path("barcode") String barcode);

   

}

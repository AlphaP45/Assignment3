package com.example.assignment3;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API_Service {
    @GET("all")
    Call<Covid19> getDataCovid();
}

package com.example.assignment3;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API_Config {
    private static final String BASE_URL = "https://corona.lmao.ninja/v2/";

    public static API_Service service() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
             return retrofit.create(API_Service.class);
    }
}

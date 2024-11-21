package com.dov.calculator.webservice;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AlbumApi {

    // For testing https://jsonplaceholder.typicode.com/photos?albumId=1
    private final String baseUrl = "https://jsonplaceholder.typicode.com/";

    private static AlbumApi instance;

    public static AlbumApi getInstance() {
        if (instance == null) {
            instance = new AlbumApi();
        }
        return instance;
    }

    public Retrofit getRetrofitClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }
}

package com.dov.calculator.webservice;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AlbumService {
    @GET("photos")
    Call<List<Photo>> getPhotos(@Query("albumId") int id);
}
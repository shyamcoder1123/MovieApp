package com.example.mymovieapp.serviceapi;

import com.example.mymovieapp.model.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieaApiService {

    @GET("movie/popular")
    Call<Result> getPopularMovies(@Query("api_key") String apiKey);
}

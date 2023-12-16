package com.example.mymovieapp.serviceapi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    // Act as a central configuration point for defining how http requests
    // should be handled.
    private static Retrofit retrofit = null;
    private static String BASE_URL = "https://api.themoviedb.org/3/";

    public static MovieaApiService getService(){
        if(retrofit==null){
            retrofit=new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(MovieaApiService.class);
    }
}

package com.example.mymovieapp.model;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.example.mymovieapp.R;
import com.example.mymovieapp.serviceapi.MovieaApiService;
import com.example.mymovieapp.serviceapi.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    //used to abstract data source detail and provides a clean
    //API for the ViewModel to fetch and manage data
    private ArrayList<Movie> movies = new ArrayList<>();
    private MutableLiveData<List<Movie>> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public MovieRepository(Application application){
        this.application=application;
    }

    public MutableLiveData<List<Movie>> getMutableLiveData(){
        MovieaApiService movieaApiService = RetrofitInstance.getService();
        Call<Result> call = movieaApiService.getPopularMovies(
                application.getApplicationContext()
                .getString(R.string.api_key)
        );
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result result = response.body();
                if(result!=null&&result.getMovieList()!=null){
                    movies = (ArrayList<Movie>) result.getMovieList();
                    mutableLiveData.setValue(movies);
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }
}

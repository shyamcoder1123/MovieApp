package com.example.mymovieapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mymovieapp.model.Movie;
import com.example.mymovieapp.model.MovieRepository;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {
    //ViewModel-suitable for non android specific logic
    //AndroidViewModel-needed when viewmodel needs to use android
    // specific components

    private MovieRepository movieRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.movieRepository = new MovieRepository(application);
    }
    public LiveData<List<Movie>> getAllMovies(){
        return movieRepository.getMutableLiveData();
    }
}

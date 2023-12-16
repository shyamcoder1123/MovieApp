package com.example.mymovieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;

import com.example.mymovieapp.databinding.ActivityMainBinding;
import com.example.mymovieapp.model.Movie;
import com.example.mymovieapp.view.MovieAdapter;
import com.example.mymovieapp.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Movie> movies;
    private MovieAdapter movieAdapter;
    private RecyclerView recyclerView;
    private MainActivityViewModel mainActivityViewModel;
    private ActivityMainBinding activityMainBinding;
    private SwipeRefreshLayout swipeRefreshLayout;
    boolean isSwipeRefreshEnabled=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        activityMainBinding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_main
        );
        mainActivityViewModel = new ViewModelProvider(this)
                .get(MainActivityViewModel.class);

        swipeRefreshLayout = activityMainBinding.swipeRefreshLayoutMovie;
        swipeRefreshLayout.setColorSchemeResources(R.color.black);
        getPopularMovies();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPopularMovies();
            }
        });

    }
    private void getPopularMovies(){
        mainActivityViewModel.getAllMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> moviesFromLivedata) {
                movies = (ArrayList<Movie>) moviesFromLivedata;
                displayMoviesInRecyclerView();
            }
        });
    }

    private void displayMoviesInRecyclerView() {
        recyclerView = activityMainBinding.movieRecyclerView;
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        movieAdapter = new MovieAdapter(this,movies);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(movieAdapter);
        movieAdapter.notifyDataSetChanged();
    }
}
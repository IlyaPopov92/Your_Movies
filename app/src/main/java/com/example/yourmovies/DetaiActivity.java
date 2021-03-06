package com.example.yourmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yourmovies.data.MainViewModel;
import com.example.yourmovies.data.Movie;
import com.squareup.picasso.Picasso;

//класс для работы с деталью из фильмов
public class DetaiActivity extends AppCompatActivity {

    private ImageView imageViewBigPoster;
    private TextView textViewTitle;
    private TextView textViewOriginalTitle;
    private TextView textViewRating;
    private TextView textViewRelease;
    private TextView textViewOverview;

    private int id;

    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detai);
        imageViewBigPoster = findViewById(R.id.imageViewBigPoster);
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewOriginalTitle = findViewById(R.id.textViewLabelOriginalTitle);
        textViewRating = findViewById(R.id.textViewRating);
        textViewRelease = findViewById(R.id.textViewRelease);
        textViewOverview = findViewById(R.id.textViewOverview);
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("id")){
            intent.getIntExtra("id", -1);
        } else {
            finish();
        }
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        Movie movie  = viewModel.getMovieById(id);
        Picasso.get().load(movie.getBigPosterPath()).into(imageViewBigPoster);
        textViewTitle.setText(movie.getTitle());
        textViewOriginalTitle.setText(movie.getOriginalTitle());
        textViewRating.setText(Double.toString(movie.getVoteAverage()));
        textViewRelease.setText(movie.getReleaseDate());
        textViewOverview.setText(movie.getOverview());

    }
}
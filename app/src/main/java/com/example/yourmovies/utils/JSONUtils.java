package com.example.yourmovies.utils;

import com.example.yourmovies.data.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

//класс для работы с объектами JSON(создаем фильм)
public class JSONUtils {
    //получаем данные из JSON для работы с фильмами, создаем ключи для доступа

    private static final String KEY_RESULT = "results";
    private static final String KEY_ID = "id";
    private static final String KEY_VOTE_COUNT = "vote_count";
    private static final String KEY_TITLE = "title";
    private static final String KEY_ORIGINAL_TITLE = "original_title";
    private static final String KEY_OVERVIEW = "overview";
    private static final String KEY_POSTER_PATH = "poster_path";
    private static final String KEY_BACKDROP_PATH = "backdrop_path";
    private static final String KEY_VOTE_AVERAGE = "vote_average";
    private static final String KEY_RELEASE_DATE = "release_date";

    public static final String BASE_POSTER_URL = "https://image.tmdb.org/t/p/";
    public static final String SMALL_POSTER_SIZE = "w185";
    public static final String BIG_POSTER_SIZE = "w780";



    //создаем метод для считывания массива с фильмами
    public static ArrayList<Movie> getMoviesFromJSON(JSONObject jsonObject) {
        ArrayList<Movie> result = new ArrayList<>();
        if (jsonObject == null) {
            return result;
        }
        try {
            JSONArray jsonArray = jsonObject.getJSONArray(KEY_RESULT);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectMovie = jsonArray.getJSONObject(i);
                int id = jsonObjectMovie.getInt(KEY_ID);
                int voteCount = jsonObjectMovie.getInt(KEY_VOTE_COUNT);
                String title = jsonObjectMovie.getString(KEY_TITLE);
                String originalTitle = jsonObjectMovie.getString(KEY_ORIGINAL_TITLE);
                String overview = jsonObjectMovie.getString(KEY_OVERVIEW);
                String posterPath = BASE_POSTER_URL + SMALL_POSTER_SIZE + jsonObjectMovie.getString(KEY_POSTER_PATH);
                String bigPosterPath = BASE_POSTER_URL + BIG_POSTER_SIZE + jsonObjectMovie.getString(KEY_POSTER_PATH);
                String backdropPath = jsonObjectMovie.getString(KEY_BACKDROP_PATH);
                double voteAverage = jsonObjectMovie.getDouble(KEY_VOTE_AVERAGE);
                String releaseDate = jsonObjectMovie.getString(KEY_RELEASE_DATE);
                Movie movie = new Movie(id, voteCount,title,originalTitle,overview,posterPath,bigPosterPath,backdropPath,voteAverage,releaseDate);
                result.add(movie);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            e.getMessage();
        }
        return result;
    }
}

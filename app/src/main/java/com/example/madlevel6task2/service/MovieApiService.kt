package com.example.madlevel6task2.service

import com.example.madlevel6task2.R
import com.example.madlevel6task2.model.Movie
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface MovieApiService {
    @GET("discover/movie?api_key=" + R.string.apikey + "&language=en-US&sort_by=popularity.desc&include_adult=true&include_video=false&page=1")
    suspend fun getMoviesByYear(@Query("primary_release_year")year: String): List<Movie>
}
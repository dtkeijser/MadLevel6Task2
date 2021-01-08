package com.example.madlevel6task2.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.madlevel6task2.model.Movie
import com.example.madlevel6task2.service.MovieApiService
import com.example.madlevel6task2.service.MoviesApi
import kotlinx.coroutines.withTimeout

class MovieRepository {

    private val movieApiService: MovieApiService = MoviesApi.createApi()

    private val _movies: MutableLiveData<List<Movie>> = MutableLiveData()

    val movies: LiveData<List<Movie>>
        get() = _movies

    suspend fun getMoviesByYear(year: String){
        try {
           val result =  withTimeout(5_000){
                movieApiService.getMoviesByYear(year)
            }
            _movies.value = result
        } catch (error:Throwable){
            throw MovieRefreshError("unable to refresh movies", error)
        }
    }

    class MovieRefreshError(message: String, cause: Throwable) : Throwable(message,cause)
}
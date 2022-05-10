package com.example.movieapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.models.Movie

class MoviesScreenViewModel : ViewModel() {
    var movies = MutableLiveData<List<Movie>>()
    fun getCharacters(){
        val movie = Movie("Biba","4", "120", "123", "13+",
        true,"src", "Action")
        val movieList : List<Movie> = listOf(movie)
        movies.value = movieList
    }
}
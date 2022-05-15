package com.example.movieapp.viewmodels.detailsFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.api.RetrofitInstance
import com.example.movieapp.models.MovieById
import kotlinx.coroutines.launch

class DetailsFragmentViewModel() : ViewModel() {
    var movieById = MutableLiveData<MovieById>()
    fun getMovieById(id : Int) {
        viewModelScope.launch {
            movieById.value = RetrofitInstance.api.getMovie(id)
        }
    }
}
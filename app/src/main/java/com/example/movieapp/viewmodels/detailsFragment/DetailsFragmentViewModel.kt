package com.example.movieapp.viewmodels.detailsFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.api.RetrofitInstance
import com.example.movieapp.models.MovieById
import kotlinx.coroutines.launch

class DetailsFragmentViewModel() : ViewModel() {
    var movieById = MutableLiveData<MovieById>()
    var exception = MutableLiveData<String>()
    fun getMovieById(id : Int) {
        viewModelScope.launch {
            RetrofitInstance.api.getMovie(id).onSuccess {
                movieById.value = it
            }
            RetrofitInstance.api.getMovie(id).onFailure {
                exception.value = it.localizedMessage
            }
        }
    }
}
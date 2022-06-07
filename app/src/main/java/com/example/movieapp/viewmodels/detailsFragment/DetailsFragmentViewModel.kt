package com.example.movieapp.viewmodels.detailsFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.api.RetrofitInstance
import com.example.movieapp.models.MovieById
import com.example.movieapp.models.similars.Similar
import kotlinx.coroutines.launch

class DetailsFragmentViewModel() : ViewModel() {
    var movieById = MutableLiveData<MovieById>()
    var similarFilms = MutableLiveData<ArrayList<Similar>>()
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

    fun getSimilars(id : Int){
        viewModelScope.launch {
            RetrofitInstance.api.getSimilars(id).onSuccess {
                similarFilms.value = it.items
            }
            RetrofitInstance.api.getSimilars(id).onFailure {
                exception.value = it.localizedMessage
            }
        }
    }
}
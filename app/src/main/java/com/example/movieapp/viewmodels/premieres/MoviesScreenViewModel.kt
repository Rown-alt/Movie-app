package com.example.movieapp.viewmodels.premieres

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.api.RetrofitInstance
import com.example.movieapp.models.Movie
import kotlinx.coroutines.launch

class MoviesScreenViewModel : ViewModel() {
    var movies = MutableLiveData<ArrayList<Movie>>()
    fun getCharacters(month : String){
       viewModelScope.launch {
           movies.value = RetrofitInstance.api.getPremieres(month).items
       }
    }
}
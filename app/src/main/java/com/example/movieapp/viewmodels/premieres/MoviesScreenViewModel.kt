package com.example.movieapp.viewmodels.premieres

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.R
import com.example.movieapp.api.RetrofitInstance
import com.example.movieapp.fragments.ErrorFragment
import com.example.movieapp.models.Movie
import kotlinx.coroutines.launch

class MoviesScreenViewModel : ViewModel() {
    var movies = MutableLiveData<ArrayList<Movie>>()
    var exception = MutableLiveData<String>()
    fun getPremieres(year : Int,month : String){
       viewModelScope.launch {
           RetrofitInstance.api.getPremieres(year,month).onSuccess {
               movies.value = it.items
           }
           RetrofitInstance.api.getPremieres(year,month).onFailure {
               exception.value = it.toString()
           }
       }
    }
}
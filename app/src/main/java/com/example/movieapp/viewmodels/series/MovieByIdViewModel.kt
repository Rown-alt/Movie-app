package com.example.movieapp.viewmodels.series

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.api.RetrofitInstance
import com.example.movieapp.models.MovieById
import kotlinx.coroutines.launch

class MovieByIdViewModel : ViewModel() {
    var series = MutableLiveData<ArrayList<MovieById>>()
    var arraySeries = ArrayList<MovieById>()
    var id = ArrayList<Int>()
    fun addSeries(){
        viewModelScope.launch {
            arraySeries.add(RetrofitInstance.api.getMovie(251568))
            arraySeries.add(RetrofitInstance.api.getMovie(401522))
            arraySeries.add(RetrofitInstance.api.getMovie(502838))
        }
    }
    fun getSeries(){
        viewModelScope.launch {
            addSeries()
            series.value = arraySeries
        }
    }

}
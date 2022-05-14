package com.example.movieapp.viewmodels.series

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.api.RetrofitInstance
import com.example.movieapp.models.MovieById
import kotlinx.coroutines.launch

class SeriesViewModel : ViewModel() {
    var series = MutableLiveData<ArrayList<MovieById>>()
    var arraySeries = ArrayList<MovieById>()
    var id = ArrayList<Int>()
    fun addSeries(id : Int){
        viewModelScope.launch {
            arraySeries.add(RetrofitInstance.api.getMovie(id))
        }
    }
    fun getSeries(){
        viewModelScope.launch {
            series.value = arraySeries
        }
    }

}
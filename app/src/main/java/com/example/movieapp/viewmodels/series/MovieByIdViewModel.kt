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
    private fun addSeries(id : Int) {
        viewModelScope.launch {
            RetrofitInstance.api.getMovie(id).onSuccess {
                arraySeries.add(it)
            }
        }
    }
    fun getSeries(){
        viewModelScope.launch {
            addSeries(251568)
            addSeries(401522)
            addSeries(502838)
            series.value = arraySeries
        }
    }

}
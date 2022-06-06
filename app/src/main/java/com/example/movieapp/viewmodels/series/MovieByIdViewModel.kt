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

    var exception = MutableLiveData<String>()

    private fun addSeries(id : Int) {
        viewModelScope.launch {
            RetrofitInstance.api.getMovie(id).onSuccess {
                arraySeries.add(it)
            }
            RetrofitInstance.api.getMovie(id).onFailure {
                exception.value = it.localizedMessage
            }
        }
    }
    fun getSeries(){
        viewModelScope.launch {
            addSeries(251568)
            addSeries(401522)
            addSeries(502838)
            addSeries(77044)
            addSeries(253245)
            addSeries(79848)
            addSeries(178710)
            series.value = arraySeries
        }
    }

}
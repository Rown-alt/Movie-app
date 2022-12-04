package com.example.movieapp.viewmodels.searchFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.api.RetrofitInstance
import com.example.movieapp.models.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    val movies = MutableLiveData<ArrayList<Movie>>()
    var exception = MutableLiveData<String>()

    fun Search(keyword : String){
        viewModelScope.launch(Dispatchers.IO){
            RetrofitInstance.api.getSearch(keyword, 1).onSuccess {
                movies.postValue(it.items)
            }
            RetrofitInstance.api.getSearch(keyword, 1).onFailure { msg->
                exception.postValue(msg.localizedMessage)
            }
        }
    }
}
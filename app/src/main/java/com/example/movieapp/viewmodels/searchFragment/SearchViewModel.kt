package com.example.movieapp.viewmodels.searchFragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.models.FiltersRequest
import com.example.movieapp.models.Movie
import com.example.movieapp.models.films_by_keyword.FilmByKeyword
import com.example.movieapp.repository.Repository
import kotlinx.coroutines.launch

class SearchViewModel(private val repository: Repository) : ViewModel() {
    val movies = MutableLiveData<ArrayList<FilmByKeyword>>()
    var exception = MutableLiveData<String>()
    var filters = MutableLiveData<FiltersRequest>()

    fun search(keyword: String){
        viewModelScope.launch{
            try{
                val request = repository.getSearch(keyword, 1)
                request.onSuccess {
                    movies.value = it.films
                }
                request.onFailure { msg->
                    exception.value = msg.localizedMessage
                    Log.e("SearchFragment", msg.localizedMessage!!)
                }
            }
            catch (exc: Exception){}

        }
    }

    fun getFilters(){
        viewModelScope.launch {
            try{
                val request = repository.getFilters()
                request.onSuccess {
                    filters.value = it
                }
                request.onFailure { msg->
                    exception.value = msg.localizedMessage
                }
            }
            catch (exc: Exception){}
        }
    }
}
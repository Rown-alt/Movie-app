package com.example.movieapp.viewmodels.searchFragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.models.Movie
import com.example.movieapp.models.films_by_keyword.FilmByKeyword
import com.example.movieapp.repository.Repository
import kotlinx.coroutines.launch

class SearchViewModel(private val repository: Repository) : ViewModel() {
    val movies = MutableLiveData<ArrayList<FilmByKeyword>>()
    var exception = MutableLiveData<String>()

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
}
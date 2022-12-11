package com.example.movieapp.viewmodels.detailsFragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.Film
import com.example.data.FilmDao
import com.example.movieapp.models.MovieById
import com.example.movieapp.models.Person
import com.example.movieapp.models.similars.Similar
import com.example.movieapp.repository.Repository
import com.example.movieapp.viewmodels.DataViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsFragmentViewModel(private val repository: Repository, filmDao: FilmDao) : DataViewModel(filmDao) {
    var movieById = MutableLiveData<MovieById>()
    var similarFilms = MutableLiveData<ArrayList<Similar>>()
    var exception = MutableLiveData<String>()

    fun getMovieById(id: Int) {
        viewModelScope.launch {
            try{
                val request = repository.getMovie(id)
                request.onSuccess {
                    movieById.value = it
                }
                request.onFailure {
                    exception.value = it.localizedMessage
                }
            }
            catch (exc: Exception){}
            
        }
    }

    fun getSimilars(id : Int){
        viewModelScope.launch {
            try{
                val request = repository.getSimilars(id)
                request.onSuccess {
                    similarFilms.value = it.items
                }
                request.onFailure {
                    exception.value = it.localizedMessage
                }
            }
            catch (exc: Exception){}
            
        }
    }
    
    var actors = MutableLiveData<List<Person>>()

    fun getStaff(id : Int){
        viewModelScope.launch {
            try{
                val request = repository.getStaff(id)
                request.onSuccess {
                    actors.value = it
                }
                request.onFailure {
                    exception.value = it.localizedMessage
                }
            }
            catch (exc: Exception){}
            
        }
    }
    
}
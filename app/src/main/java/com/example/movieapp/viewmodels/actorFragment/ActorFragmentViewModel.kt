package com.example.movieapp.viewmodels.actorFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.api.RetrofitInstance
import com.example.movieapp.models.actorById.ActorById
import com.example.movieapp.models.actorById.Films
import com.example.movieapp.models.MovieById
import kotlinx.coroutines.launch

class ActorFragmentViewModel : ViewModel() {
    var actorById = MutableLiveData<ActorById>()
    var filmsById = MutableLiveData<ArrayList<MovieById>>()

    private var arrayFilms = ArrayList<Films>()
    private var shortArrayFilms = ArrayList<Films>()
    private var arrayFilmsById = ArrayList<MovieById>()

    var exception = MutableLiveData<String>()

    fun getActorById(id : Int) {
        viewModelScope.launch {
            RetrofitInstance.api.getActorById(id).onSuccess {
                actorById.value = it
            }
            RetrofitInstance.api.getActorById(id).onFailure {
                exception.value = it.localizedMessage
            }
        }
    }
    fun getMovies(id: Int){
        viewModelScope.launch {
            RetrofitInstance.api.getActorById(id).onSuccess {
                arrayFilms = it.films
            }
            RetrofitInstance.api.getActorById(id).onFailure {
                exception.value = it.localizedMessage
            }
            if (arrayFilms.size <= 3){
                for (i in arrayFilms){
                    RetrofitInstance.api.getMovie(i.filmId).onSuccess {
                        arrayFilmsById.add(it)
                    }
                    RetrofitInstance.api.getMovie(i.filmId).onFailure {
                        exception.value = it.localizedMessage
                    }
                }
            }
            else{
                for (i in 0..3){
                    RetrofitInstance.api.getMovie(arrayFilms[i].filmId).onSuccess {
                        arrayFilmsById.add(it)
                    }
                    RetrofitInstance.api.getMovie(arrayFilms[i].filmId).onFailure {
                        exception.value = it.localizedMessage
                    }
                }
            }
            filmsById.value = arrayFilmsById
        }
    }

}
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
    var arrayFilmsById = ArrayList<MovieById>()
    fun getActorById(id : Int) {
        viewModelScope.launch {
            actorById.value = RetrofitInstance.api.getActorById(id)
        }
    }
    fun getMovies(id: Int){
        viewModelScope.launch {
            arrayFilms = RetrofitInstance.api.getActorById(id).films

            if (arrayFilms.size <= 5){
                for (i in arrayFilms){
                    arrayFilmsById.add(RetrofitInstance.api.getMovie(i.filmId))
                }
            }
            else{
                for (i in 0..5){
                    arrayFilmsById.add((RetrofitInstance.api.getMovie(arrayFilms[i].filmId)))
                }
            }
            filmsById.value = arrayFilmsById
        }
    }

}
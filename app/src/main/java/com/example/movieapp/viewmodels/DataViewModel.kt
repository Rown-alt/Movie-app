package com.example.movieapp.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.Film
import com.example.data.FilmDao
import com.example.movieapp.models.MovieById
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class DataViewModel(private val filmDao: FilmDao): ViewModel() {
    var favouriteState = MutableLiveData<Boolean>()
    var films = MutableLiveData<List<Film>>()

    fun addFilm(film: MovieById){
        viewModelScope.launch(Dispatchers.IO) {
            filmDao.insertAll(Film(film.kinopoiskId!!, film.nameRu, film.posterUrlPreview))
        }
    }

    fun deleteFilm(film: MovieById){
        viewModelScope.launch(Dispatchers.IO) {
            filmDao.delete(Film(film.kinopoiskId!!, film.nameRu, film.posterUrlPreview))
        }
    }

    fun checkFavourite(filmId: Int){
        viewModelScope.launch(Dispatchers.IO){
            try{
                val film = filmDao.findById(filmId)
                favouriteState.postValue(true)
                Log.e("FilmsDaoFilm", film.toString())
            } catch (exc: java.lang.NullPointerException){
                Log.e("FilmsDao", exc.toString())
                favouriteState.postValue(false)
            }
        }
    }

    fun getFilms(){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                films.postValue(filmDao.getAll())
            }
            catch (exc: Exception){
                Log.e("FilmsFavourite", exc.toString())
            }
        }
    }
}
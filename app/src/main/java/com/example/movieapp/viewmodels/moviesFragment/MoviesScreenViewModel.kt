package com.example.movieapp.viewmodels.moviesFragment


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.api.RetrofitInstance
import com.example.movieapp.models.Movie
import com.example.movieapp.models.top_of_films.FilmsTop
import kotlinx.coroutines.launch
import kotlin.random.Random

class MoviesScreenViewModel : ViewModel() {

    var movies = MutableLiveData<ArrayList<Movie>>()
    var randomFilm = MutableLiveData<FilmsTop>()
    var top = MutableLiveData<ArrayList<FilmsTop>>()
    var topArray = ArrayList<FilmsTop>()
    private var randomNum = Random.nextInt(0, 10)

    var exception = MutableLiveData<String>()

    fun getPremieres(year : Int,month : String){
       viewModelScope.launch {
           RetrofitInstance.api.getPremieres(year,month).onSuccess {
               movies.value = it.items
           }
           RetrofitInstance.api.getPremieres(year,month).onFailure {
               exception.value = it.localizedMessage
           }
       }
    }
    private fun addTop(type : String, page : Int)
    {
        viewModelScope.launch {
            RetrofitInstance.api.getTop(type, page).onSuccess {
                Log.e("Request", "Request sent")
                val randomPage = Random.nextInt(1, it.pagesCount)
                RetrofitInstance.api.getTop(type, randomPage).onSuccess {
                    for (i in randomNum..randomNum+9){
                        topArray.add(it.films[i])
                    }
                }
                RetrofitInstance.api.getTop(type, randomPage).onFailure {
                    exception.value = it.localizedMessage
                }
            }
            RetrofitInstance.api.getTop(type, page).onFailure {
                exception.value = it.localizedMessage
            }
        }
    }
    fun getTop(type : String ){
        viewModelScope.launch {
           addTop(type, 1)
           top.value = topArray
        }
    }
    fun getRandomFilm(){
        viewModelScope.launch {
            val randomId = Random.nextInt(1, 20)
            RetrofitInstance.api.getTop("TOP_100_POPULAR_FILMS", 1).onSuccess {
                randomFilm.value = it.films[randomId]
            }
            RetrofitInstance.api.getTop("TOP_100_POPULAR_FILMS", 1).onFailure {
                exception.value = it.localizedMessage
            }
        }
    }
}
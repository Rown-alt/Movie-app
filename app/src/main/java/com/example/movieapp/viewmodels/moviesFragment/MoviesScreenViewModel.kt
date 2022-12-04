package com.example.movieapp.viewmodels.moviesFragment


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.api.RetrofitInstance
import com.example.movieapp.models.Movie
import com.example.movieapp.models.MovieById
import com.example.movieapp.models.top_of_films.FilmsTop
import com.example.movieapp.repository.Repository
import kotlinx.coroutines.launch
import java.lang.Exception
import kotlin.random.Random

class MoviesScreenViewModel(private val repository: Repository) : ViewModel() {
    var movies = MutableLiveData<ArrayList<Movie>>()
    var randomFilm = MutableLiveData<FilmsTop>()
    var topMovies = MutableLiveData<ArrayList<FilmsTop>>()
    var exception = MutableLiveData<String>()

    fun getPremieres(year : Int,month : String){
       viewModelScope.launch{
           try{
               val request = repository.getPremieres(year,month)
               request.onSuccess {
                   movies.value = it.items
               }
               request.onFailure { msg->
                   exception.value = msg.localizedMessage
               }
           }
           catch (exc: Exception){}
       }
    }

    fun getTop(type : String, page : Int) {
        viewModelScope.launch{
            try{
                val request = repository.getTop(type, page)
                request.onSuccess {
                    topMovies.value = it.films
                }
                request.onFailure { msg->
                    exception.value = msg.localizedMessage
                }
            }
            catch (exc: Exception){}
        }
    }

    fun getRandomFilm(){
        viewModelScope.launch {
            try{
                val randomId = Random.nextInt(1, 20)
                val request = repository.getTop("TOP_100_POPULAR_FILMS", 1)
                request.onSuccess {
                    val film = it.films[randomId]
                    randomFilm.value = film
                }
                request.onFailure { msg->
                    exception.value = msg.localizedMessage
                }
            }
            catch (exc: Exception){}
        }
    }
    var series = MutableLiveData<ArrayList<MovieById>>()
    var arraySeries = ArrayList<MovieById>()

    private fun addSeries(id : Int) {
        viewModelScope.launch {
            try {
                val request = RetrofitInstance.api.getMovie(id)
                request.onSuccess {
                    arraySeries.add(it)
                }
                request.onFailure {
                    exception.value = it.localizedMessage
                }
            }
            catch (exc: Exception){}

        }
    }
    fun getSeries(){
        viewModelScope.launch{
            addSeries(178710)
            series.value = arraySeries
        }
        arraySeries.clear()
    }
}
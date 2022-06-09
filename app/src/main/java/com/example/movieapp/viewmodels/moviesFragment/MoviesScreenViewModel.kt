package com.example.movieapp.viewmodels.moviesFragment


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.api.RetrofitInstance
import com.example.movieapp.models.Movie
import com.example.movieapp.models.top_of_films.FilmsTop
import kotlinx.coroutines.Dispatchers
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
       viewModelScope.launch(Dispatchers.IO){
           RetrofitInstance.api.getPremieres(year,month).onSuccess {
               movies.postValue(it.items)
           }
           RetrofitInstance.api.getPremieres(year,month).onFailure { msg->
               exception.postValue(msg.localizedMessage)
           }
       }
    }

    private fun addTop(type : String, page : Int)
    {
        viewModelScope.launch(Dispatchers.IO) {

            RetrofitInstance.api.getTop(type, page).onSuccess {
                Log.e("Request", "Request sent")
                val randomPage = Random.nextInt(1, it.pagesCount)
                RetrofitInstance.api.getTop(type, randomPage).onSuccess { topOfFilms->
                    for (i in randomNum..randomNum+9){
                        topArray.add(topOfFilms.films[i])
                    }
                }
                RetrofitInstance.api.getTop(type, randomPage).onFailure { msg->
                    exception.postValue(msg.localizedMessage)
                }
            }

            RetrofitInstance.api.getTop(type, page).onFailure { msg->
                exception.postValue(msg.localizedMessage)
            }
        }
    }

    fun getTop(type : String ){
        viewModelScope.launch(Dispatchers.IO) {
           addTop(type, 1)
           top.postValue(topArray)
        }
    }

    fun getRandomFilm(){
        viewModelScope.launch {
            val randomId = Random.nextInt(1, 20)

            RetrofitInstance.api.getTop("TOP_100_POPULAR_FILMS", 1).onSuccess {
                val film = it.films[randomId]
                randomFilm.postValue(film)
            }

            RetrofitInstance.api.getTop("TOP_100_POPULAR_FILMS", 1).onFailure { msg->
                exception.postValue(msg.localizedMessage)
            }

        }
    }
}
package com.example.movieapp.viewmodels.ActorFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.api.RetrofitInstance
import com.example.movieapp.models.MovieById
import com.example.movieapp.models.Person
import kotlinx.coroutines.launch

class ActorFragmentViewModel : ViewModel() {
    var actorById = MutableLiveData<Person>()
    fun getActorById(id : Int) {
        viewModelScope.launch {
            actorById.value = RetrofitInstance.api.getActorById(id)
        }
    }
}
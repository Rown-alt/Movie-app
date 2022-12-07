package com.example.movieapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.models.actorById.ActorById
import com.example.movieapp.repository.Repository
import kotlinx.coroutines.launch

class ActorViewModel(private val repository: Repository): ViewModel() {
    var actor = MutableLiveData<ActorById>()
    var exception = MutableLiveData<String>()

    fun getActorById(actorId: Int){
        viewModelScope.launch {
            try{
                val request = repository.getActorById(actorId)
                request.onSuccess {
                    actor.value = it
                }
                request.onFailure {
                    exception.value = it.localizedMessage
                }
            }
            catch (_: Exception){}
        }
    }
}
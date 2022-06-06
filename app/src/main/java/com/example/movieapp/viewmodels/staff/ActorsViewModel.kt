package com.example.movieapp.viewmodels.staff

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.api.RetrofitInstance
import com.example.movieapp.models.Person
import kotlinx.coroutines.launch

class ActorsViewModel : ViewModel() {
    var actors = MutableLiveData<List<Person>>()

    var exception = MutableLiveData<String>()

    fun getStaff(id : Int){
        viewModelScope.launch {
            RetrofitInstance.api.getStaff(id).onSuccess {
                actors.value = it
            }
            RetrofitInstance.api.getStaff(id).onFailure {
                exception.value = it.localizedMessage
            }
        }
    }
}
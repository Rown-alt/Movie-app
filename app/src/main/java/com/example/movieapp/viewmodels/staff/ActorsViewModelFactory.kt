package com.example.movieapp.viewmodels.staff

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ActorsViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ActorsViewModel() as T
    }
}
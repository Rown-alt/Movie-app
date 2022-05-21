package com.example.movieapp.viewmodels.actorFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ActorFragmentViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ActorFragmentViewModel() as T
    }
}
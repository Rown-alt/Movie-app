package com.example.movieapp.viewmodels.premieres

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MoviesScreenViemModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
         return MoviesScreenViewModel() as T
    }

}
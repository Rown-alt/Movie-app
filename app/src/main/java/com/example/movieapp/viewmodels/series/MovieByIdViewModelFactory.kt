package com.example.movieapp.viewmodels.series

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MovieByIdViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieByIdViewModel() as T
    }
}
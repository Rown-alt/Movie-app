package com.example.movieapp.viewmodels.detailsFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DetailsFragmentViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailsFragmentViewModel() as T
    }
}
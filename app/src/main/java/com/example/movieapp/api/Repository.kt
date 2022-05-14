package com.example.movieapp.api

import com.example.movieapp.models.MovieList

class Repository {
    suspend fun getPremieres(month : String) : MovieList{
        return RetrofitInstance.api.getPremieres(month)
    }
}
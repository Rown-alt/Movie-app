package com.example.movieapp.models

import com.google.gson.annotations.SerializedName

data class MovieList(
    @SerializedName("total" ) var total : Int?             = null,
    @SerializedName("items" ) var items : ArrayList<Movie> = arrayListOf()
)

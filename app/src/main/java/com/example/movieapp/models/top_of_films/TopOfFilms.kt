package com.example.movieapp.models.top_of_films

import com.google.gson.annotations.SerializedName


data class TopOfFilms (

    @SerializedName("pagesCount" ) var pagesCount : Int                            ,
    @SerializedName("films"      ) var films      : ArrayList<FilmsTop> = arrayListOf()

)

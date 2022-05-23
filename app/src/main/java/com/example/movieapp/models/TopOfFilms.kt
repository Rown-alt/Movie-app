package com.example.movieapp.models

import com.example.movieapp.models.actorById.Films
import com.google.gson.annotations.SerializedName


data class TopOfFilms (

    @SerializedName("pagesCount" ) var pagesCount : Int                            ,
    @SerializedName("films"      ) var films      : ArrayList<Films> = arrayListOf()

)

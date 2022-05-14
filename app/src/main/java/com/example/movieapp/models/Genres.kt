package com.example.movieapp.models

import com.google.gson.annotations.SerializedName

data class Genres(
    @SerializedName("genre" ) var genre : String? = null
)

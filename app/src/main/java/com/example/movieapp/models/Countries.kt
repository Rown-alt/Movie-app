package com.example.movieapp.models

import com.google.gson.annotations.SerializedName

data class Countries(
    @SerializedName("country" ) var country : String? = null
)

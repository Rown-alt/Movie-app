package com.example.movieapp.models.similars

import com.google.gson.annotations.SerializedName

data class SimilarsList(
    @SerializedName("total" ) var total : Int?             = null,
    @SerializedName("items" ) var items : ArrayList<Similar> = arrayListOf()
)
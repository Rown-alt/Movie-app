package com.example.movieapp.models.actorById

import com.google.gson.annotations.SerializedName

data class Films(
    @SerializedName("filmId"        ) var filmId        : Int            ,
    @SerializedName("nameRu"        ) var nameRu        : String?  = null,
    @SerializedName("nameEn"        ) var nameEn        : String?  = null,
    @SerializedName("rating"        ) var rating        : String?  = null,
    @SerializedName("general"       ) var general       : Boolean? = null,
    @SerializedName("description"   ) var description   : String?  = null,
    @SerializedName("professionKey" ) var professionKey : String?  = null
)

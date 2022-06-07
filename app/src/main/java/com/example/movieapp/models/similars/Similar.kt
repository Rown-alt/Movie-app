package com.example.movieapp.models.similars

import com.example.movieapp.models.Countries
import com.example.movieapp.models.Genres
import com.google.gson.annotations.SerializedName

data class Similar(
    @SerializedName("kinopoiskId"      ) var kinopoiskId      : Int,
    @SerializedName("nameRu"           ) var nameRu           : String?              = null,
    @SerializedName("nameEn"           ) var nameEn           : String?              = null,
    @SerializedName("posterUrl"        ) var posterUrl        : String?              = null,
    @SerializedName("posterUrlPreview" ) var posterUrlPreview : String?              = null,
    @SerializedName("relationType"       ) var relationType       : String?              = null
)
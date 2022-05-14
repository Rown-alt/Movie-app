package com.example.movieapp.models

import com.google.gson.annotations.SerializedName


data class Movie (
    @SerializedName("kinopoiskId"      ) var kinopoiskId      : Int?                 = null,
    @SerializedName("nameRu"           ) var nameRu           : String?              = null,
    @SerializedName("nameEn"           ) var nameEn           : String?              = null,
    @SerializedName("year"             ) var year             : Int?                 = null,
    @SerializedName("posterUrl"        ) var posterUrl        : String?              = null,
    @SerializedName("posterUrlPreview" ) var posterUrlPreview : String?              = null,
    @SerializedName("countries"        ) var countries        : ArrayList<Countries> = arrayListOf(),
    @SerializedName("genres"           ) var genres           : ArrayList<Genres>    = arrayListOf(),
    @SerializedName("duration"         ) var duration         : Int?                 = null,
    @SerializedName("premiereRu"       ) var premiereRu       : String?              = null
)

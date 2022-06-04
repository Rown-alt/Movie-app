package com.example.movieapp.models.top_of_films

import com.example.movieapp.models.Countries
import com.example.movieapp.models.Genres
import com.google.gson.annotations.SerializedName

data class FilmsTop (
    @SerializedName("filmId"           ) var filmId           : Int                        ,
    @SerializedName("nameRu"           ) var nameRu           : String?              = null,
    @SerializedName("nameEn"           ) var nameEn           : String?              = null,
    @SerializedName("year"             ) var year             : String?              = null,
    @SerializedName("filmLength"       ) var filmLength       : String?              = null,
    @SerializedName("countries"        ) var countries        : ArrayList<Countries> = arrayListOf(),
    @SerializedName("genres"           ) var genres           : ArrayList<Genres>    = arrayListOf(),
    @SerializedName("rating"           ) var rating           : String               = "0.0",
    @SerializedName("ratingVoteCount"  ) var ratingVoteCount  : Int?                 = null,
    @SerializedName("posterUrl"        ) var posterUrl        : String?              = null,
    @SerializedName("posterUrlPreview" ) var posterUrlPreview : String?              = null,
    @SerializedName("ratingChange"     ) var ratingChange     : String?              = null
)
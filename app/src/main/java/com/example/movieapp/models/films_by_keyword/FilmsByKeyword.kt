package com.example.movieapp.models.films_by_keyword

import com.example.movieapp.models.Countries
import com.example.movieapp.models.Genres
import com.example.movieapp.models.Movie
import com.google.gson.annotations.SerializedName


data class FilmsByKeyword(
    @SerializedName("keyword") val keyword: String? = null,
    @SerializedName("pagesCount") val pagesCount: Int? = null,
    @SerializedName("films") val films: ArrayList<FilmByKeyword> = arrayListOf()
)

data class FilmByKeyword(
    @SerializedName("filmId"           ) var filmId           : Int?                 = null,
    @SerializedName("nameRu"           ) var nameRu           : String?              = null,
    @SerializedName("nameEn"           ) var nameEn           : String?              = null,
    @SerializedName("type"             ) var type             : String?              = null,
    @SerializedName("year"             ) var year             : String?              = null,
    @SerializedName("description"      ) var description      : String?              = null,
    @SerializedName("filmLength"       ) var filmLength       : String?              = null,
    @SerializedName("countries"        ) var countries        : ArrayList<Countries> = arrayListOf(),
    @SerializedName("genres"           ) var genres           : ArrayList<Genres>    = arrayListOf(),
    @SerializedName("rating"           ) var rating           : String?              = null,
    @SerializedName("ratingVoteCount"  ) var ratingVoteCount  : Int?                 = null,
    @SerializedName("posterUrl"        ) var posterUrl        : String?              = null,
    @SerializedName("posterUrlPreview" ) var posterUrlPreview : String?              = null
)

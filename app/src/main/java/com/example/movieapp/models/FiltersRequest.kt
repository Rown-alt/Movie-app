package com.example.movieapp.models

import com.google.gson.annotations.SerializedName

data class FiltersRequest(
    @SerializedName("genres") var genres: List<Genre> = listOf(),
    @SerializedName("countries") val countries: ArrayList<Country> = arrayListOf()

) {
    data class Genre (
        @SerializedName("id") val id: Int? = null,
        @SerializedName("genre") val genre: String? = null,
        var state: Boolean = false
    )

    data class Country (
        @SerializedName("id") val id: Int? = null,
        @SerializedName("country") val country: String? = null,
        var state: Boolean = false
    )
}

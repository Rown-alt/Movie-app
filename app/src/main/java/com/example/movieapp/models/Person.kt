package com.example.movieapp.models

import com.google.gson.annotations.SerializedName

data class Person(
    @SerializedName("staffId"        ) var staffId        : Int?    = null,
    @SerializedName("nameRu"         ) var nameRu         : String? = null,
    @SerializedName("nameEn"         ) var nameEn         : String? = null,
    @SerializedName("description"    ) var description    : String? = null,
    @SerializedName("posterUrl"      ) var posterUrl      : String? = null,
    @SerializedName("professionText" ) var professionText : String? = null,
    @SerializedName("professionKey"  ) var professionKey  : String? = null
)


package com.example.movieapp.models.actorById

import com.google.gson.annotations.SerializedName

data class Spouses(
    @SerializedName("personId"       ) var personId       : Int            ,
    @SerializedName("name"           ) var name           : String?  = null,
    @SerializedName("divorced"       ) var divorced       : Boolean? = null,
    @SerializedName("divorcedReason" ) var divorcedReason : String?  = null,
    @SerializedName("sex"            ) var sex            : String?  = null,
    @SerializedName("children"       ) var children       : Int?     = null,
    @SerializedName("webUrl"         ) var webUrl         : String?  = null,
    @SerializedName("relation"       ) var relation       : String?  = null
)

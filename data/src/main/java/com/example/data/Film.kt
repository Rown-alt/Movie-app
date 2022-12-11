package com.example.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Film (
    @PrimaryKey val filmId: Int,
    @ColumnInfo (name = "film_name") val filmName: String?,
    @ColumnInfo (name = "logo") val logo : String?
)
package com.example.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FilmDao {
    @Query("SELECT * FROM film")
    fun getAll(): List<Film>

    @Query("SELECT * FROM film WHERE filmId=:id")
    fun findById(id: Int): Film

    @Query("SELECT * FROM film WHERE filmId IN (:filmIds)")
    fun loadAllByIds(filmIds: IntArray): List<Film>

    @Insert
    fun insertAll(vararg films: Film)

    @Delete
    fun delete(film: Film)
}
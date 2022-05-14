package com.example.movieapp.api

import com.example.movieapp.models.MovieById
import com.example.movieapp.models.MovieList
import com.example.movieapp.models.Staff
import retrofit2.http.*

interface KinoPoiskAPI {
    @Headers("X-API-KEY: 9dea54ec-4608-424b-98a4-3cd3b76af31e")
    @GET("v2.2/films/premieres?year=2001")
    suspend fun getPremieres(@Query("month") month : String) : MovieList
    @Headers("X-API-KEY: 9dea54ec-4608-424b-98a4-3cd3b76af31e")
    @GET("v2.2/films/{Id}")
    suspend fun getMovie(@Path("Id") movieId : Int) : MovieById
    @Headers("X-API-KEY: 9dea54ec-4608-424b-98a4-3cd3b76af31e")
    @GET("v1/staff")
    suspend fun  getStaff(@Query("filmId") movieID : Int) : Staff
}
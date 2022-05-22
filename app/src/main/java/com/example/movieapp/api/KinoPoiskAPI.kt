package com.example.movieapp.api

import com.example.movieapp.models.actorById.ActorById
import com.example.movieapp.models.MovieById
import com.example.movieapp.models.MovieList
import com.example.movieapp.models.Person
import retrofit2.http.*

interface KinoPoiskAPI {
    @Headers("X-API-KEY: 9dea54ec-4608-424b-98a4-3cd3b76af31e")
    @GET("v2.2/films/premieres?year=2002")
    suspend fun getPremieres(@Query("year") year : Int ,@Query("month") month : String) : Result<MovieList>
    @Headers("X-API-KEY: 9dea54ec-4608-424b-98a4-3cd3b76af31e")
    @GET("v2.2/films/{id}")
    suspend fun getMovie(@Path("id") id : Int) : Result<MovieById>
    @Headers("X-API-KEY: 9dea54ec-4608-424b-98a4-3cd3b76af31e")
    @GET("v1/staff")
    suspend fun  getStaff(@Query("filmId") movieID : Int) : Result<List<Person>>
    @Headers("X-API-KEY: 9dea54ec-4608-424b-98a4-3cd3b76af31e")
    @GET("v1/staff/{id}")
    suspend fun getActorById(@Path("id") actorId : Int) : Result<ActorById>
}
package com.example.movieapp.api

import com.example.movieapp.models.actorById.ActorById
import com.example.movieapp.models.MovieById
import com.example.movieapp.models.MovieList
import com.example.movieapp.models.Person
import com.example.movieapp.models.similars.SimilarsList
import com.example.movieapp.models.top_of_films.TopOfFilms
import retrofit2.http.*

interface KinoPoiskAPI {
    @Headers("X-API-KEY: 9dea54ec-4608-424b-98a4-3cd3b76af31e")
    @GET("v2.2/films/premieres")
    suspend fun getPremieres(@Query("year") year : Int ,@Query("month") month : String) : Result<MovieList>

    @Headers("X-API-KEY: 9dea54ec-4608-424b-98a4-3cd3b76af31e")
    @GET("v2.2/films/{id}")
    suspend fun getMovie(@Path("id") id : Int) : Result<MovieById>

    @Headers("X-API-KEY: 9dea54ec-4608-424b-98a4-3cd3b76af31e")
    @GET("v1/staff")
    suspend fun  getStaff(@Query("filmId") movieId : Int) : Result<List<Person>>

    @Headers("X-API-KEY: 9dea54ec-4608-424b-98a4-3cd3b76af31e")
    @GET("v1/staff/{id}")
    suspend fun getActorById(@Path("id") actorId : Int) : Result<ActorById>

    @Headers("X-API-KEY: 9dea54ec-4608-424b-98a4-3cd3b76af31e")
    @GET("v2.2/films/top")
    suspend fun getTop(@Query("type") type : String, @Query("page") page : Int) : Result<TopOfFilms>

    @Headers("X-API-KEY: 9dea54ec-4608-424b-98a4-3cd3b76af31e")
    @GET("v2.2/films/{id}/similars")
    suspend fun getSimilars(@Path("id") movieId : Int) : Result<SimilarsList>
}
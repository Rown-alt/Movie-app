package com.example.movieapp.api

import com.example.movieapp.models.FiltersRequest
import com.example.movieapp.models.actorById.ActorById
import com.example.movieapp.models.MovieById
import com.example.movieapp.models.MovieList
import com.example.movieapp.models.Person
import com.example.movieapp.models.films_by_keyword.FilmsByKeyword
import com.example.movieapp.models.similars.SimilarsList
import com.example.movieapp.models.top_of_films.TopOfFilms
import retrofit2.http.*
const val header = "X-API-KEY: 9dea54ec-4608-424b-98a4-3cd3b76af31e"
interface KinoPoiskAPI {
    @Headers(header)
    @GET("v2.2/films/premieres")
    suspend fun getPremieres(@Query("year") year : Int ,@Query("month") month : String) : Result<MovieList>

    @Headers(header)
    @GET("v2.2/films/{id}")
    suspend fun getMovie(@Path("id") id : Int) : Result<MovieById>

    @Headers(header)
    @GET("v1/staff")
    suspend fun  getStaff(@Query("filmId") movieId : Int) : Result<List<Person>>

    @Headers(header)
    @GET("v1/staff/{id}")
    suspend fun getActorById(@Path("id") actorId : Int) : Result<ActorById>

    @Headers(header)
    @GET("v2.2/films/top")
    suspend fun getTop(@Query("type") type : String, @Query("page") page : Int) : Result<TopOfFilms>

    @Headers(header)
    @GET("v2.2/films/{id}/similars")
    suspend fun getSimilars(@Path("id") movieId : Int) : Result<SimilarsList>

    @Headers(header)
    @GET("v2.1/films/search-by-keyword")
    suspend fun getSearch(@Query("keyword") keyword : String, @Query("page") page : Int) : Result<FilmsByKeyword>

    @Headers(header)
    @GET("v2.2/films/filters")
    suspend fun getFilters(): Result<FiltersRequest>
}
package com.example.movieapp.repository

import com.example.movieapp.api.header
import com.example.movieapp.models.FiltersRequest
import com.example.movieapp.models.MovieById
import com.example.movieapp.models.MovieList
import com.example.movieapp.models.Person
import com.example.movieapp.models.actorById.ActorById
import com.example.movieapp.models.films_by_keyword.FilmsByKeyword
import com.example.movieapp.models.similars.SimilarsList
import com.example.movieapp.models.top_of_films.TopOfFilms
import retrofit2.http.Path
import retrofit2.http.Query

interface Repository {
    suspend fun getPremieres(@Query("year") year : Int, @Query("month") month : String) : Result<MovieList>

    suspend fun getMovie(@Path("id") id : Int) : Result<MovieById>

    suspend fun  getStaff(@Query("filmId") movieId : Int) : Result<List<Person>>

    suspend fun getActorById(@Path("id") actorId : Int) : Result<ActorById>

    suspend fun getTop(@Query("type") type : String, @Query("page") page : Int) : Result<TopOfFilms>

    suspend fun getSimilars(@Path("id") movieId : Int) : Result<SimilarsList>

    suspend fun getSearch(@Query("keyword") keyword : String, @Query("page") page : Int) : Result<FilmsByKeyword>

    suspend fun getFilters(): Result<FiltersRequest>
}
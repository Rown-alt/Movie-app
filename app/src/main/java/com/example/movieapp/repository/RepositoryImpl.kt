package com.example.movieapp.repository

import com.example.movieapp.api.KinoPoiskAPI
import com.example.movieapp.models.MovieById
import com.example.movieapp.models.MovieList
import com.example.movieapp.models.Person
import com.example.movieapp.models.actorById.ActorById
import com.example.movieapp.models.similars.SimilarsList
import com.example.movieapp.models.top_of_films.TopOfFilms

class RepositoryImpl(private val api: KinoPoiskAPI): Repository {
    override suspend fun getPremieres(year: Int, month: String): Result<MovieList> {
        return api.getPremieres(year, month)
    }

    override suspend fun getMovie(id: Int): Result<MovieById> {
        return api.getMovie(id)
    }

    override suspend fun getStaff(movieId: Int): Result<List<Person>> {
        return api.getStaff(movieId)
    }

    override suspend fun getActorById(actorId: Int): Result<ActorById> {
        return api.getActorById(actorId)
    }

    override suspend fun getTop(type: String, page: Int): Result<TopOfFilms> {
        return api.getTop(type, page)
    }

    override suspend fun getSimilars(movieId: Int): Result<SimilarsList> {
        return api.getSimilars(movieId)
    }

    override suspend fun getSearch(keyword: String, page: Int): Result<MovieList> {
        return api.getSearch(keyword, page)
    }
}
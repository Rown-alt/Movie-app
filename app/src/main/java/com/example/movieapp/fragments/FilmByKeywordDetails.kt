package com.example.movieapp.fragments

import android.content.Context
import androidx.navigation.fragment.navArgs
import com.example.movieapp.fragments.abstractions.FilmDetailsAbstractFragment

class FilmByKeywordDetails: FilmDetailsAbstractFragment() {
    private val filmArgs: FilmByKeywordDetailsArgs by navArgs()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        setFilmId(filmArgs.filmId)
        actorAdapter.filmType = "FilmByKeyword"
    }
}
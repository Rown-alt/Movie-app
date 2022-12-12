package com.example.movieapp.fragments.favourites

import android.content.Context
import androidx.navigation.fragment.navArgs
import com.example.movieapp.fragments.abstractions.FilmDetailsAbstractFragment

class FavouritesDetailsFragment: FilmDetailsAbstractFragment() {
    private val filmArgs: FavouritesDetailsFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        setFilmId(filmArgs.filmId)
        actorAdapter.filmType = "Favourites"
    }
}
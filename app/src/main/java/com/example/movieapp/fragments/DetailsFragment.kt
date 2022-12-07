package com.example.movieapp.fragments

import android.content.Context
import androidx.navigation.fragment.navArgs
import com.example.movieapp.fragments.abstractions.FilmDetailsAbstractFragment

class DetailsFragment : FilmDetailsAbstractFragment() {
    private val filmArgs: DetailsFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        setFilmId(filmArgs.filmId)
    }
}
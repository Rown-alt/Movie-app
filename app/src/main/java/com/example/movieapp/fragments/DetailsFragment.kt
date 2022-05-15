package com.example.movieapp.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.adapter.ActorsAdapter
import com.example.movieapp.models.MovieById
import com.example.movieapp.models.Staff
import com.example.movieapp.viewmodels.detailsFragment.DetailsFragmentViewModel
import com.example.movieapp.viewmodels.staff.ActorsViewModel

class DetailsFragment : Fragment(R.layout.details_screen) {
    val detailsFragmentViewModel : DetailsFragmentViewModel by viewModels()
    val actorsViewModel : ActorsViewModel by viewModels()
    private lateinit var movieById : MovieById
    private lateinit var staff : Staff
    private var actorAdapter = ActorsAdapter()
    val detailsArgs : DetailsFragmentArgs by navArgs()
    private lateinit var recyclerViewActors : RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val filmId : Int = detailsArgs.filmId
        detailsFragmentViewModel.getMovieById(filmId)
        recyclerViewActors = view.findViewById(R.id.actorsRV)
        val cover : ImageView = view.findViewById(R.id.cover)
        val logo : ImageView = view.findViewById(R.id.logo)
        val nameOriginal : TextView = view.findViewById(R.id.nameOriginal)
        val rating : TextView = view.findViewById(R.id.rating)
        val ratingAgeLimits : TextView = view.findViewById(R.id.ratingAgeLimits)
        val duration : TextView = view.findViewById(R.id.duration)
        val year : TextView = view.findViewById(R.id.year)
        val country : TextView = view.findViewById(R.id.country)
        val genresDetail : TextView = view.findViewById(R.id.genresDetails)
        val description : TextView = view.findViewById(R.id.description)
        var ageLimit : String?
        detailsFragmentViewModel.movieById.observe(viewLifecycleOwner){
            movieById = it
            Glide.with(view).load(movieById.coverUrl).into(cover)
            Glide.with(view).load(movieById.logoUrl).into(logo)
            nameOriginal.text = movieById.nameOriginal
            rating.text = movieById.ratingKinopoisk.toString()
            ageLimit = movieById.ratingAgeLimits?.filter { it.isDigit() }
            ratingAgeLimits.text = ageLimit + "+"
            duration.text = movieById.filmLength.toString() + "мин, "
            year.text = movieById.year.toString()
            country.text = movieById.countries[0].country
            genresDetail.text = movieById.genres[0].genre
            description.text = movieById.description
        }


//        actorsViewModel.actors.observe(viewLifecycleOwner){
//            actorAdapter.setActors(it)
//        }
//        recyclerViewActors.adapter = actorAdapter
//        recyclerViewActors.layoutManager = StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.HORIZONTAL)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        //actorsViewModel.getStaff(detailsArgs.filmId)
    }
}
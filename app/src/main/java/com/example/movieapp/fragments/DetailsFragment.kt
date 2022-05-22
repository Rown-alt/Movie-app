package com.example.movieapp.fragments

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.adapter.ActorsAdapter
import com.example.movieapp.api.RetrofitInstance
import com.example.movieapp.viewmodels.detailsFragment.DetailsFragmentViewModel
import com.example.movieapp.viewmodels.staff.ActorsViewModel

class DetailsFragment : Fragment(R.layout.details_screen) {
    private val detailsFragmentViewModel : DetailsFragmentViewModel by viewModels()
    private val actorsViewModel : ActorsViewModel by viewModels()
    private var actorAdapter = ActorsAdapter()
    private val detailsArgs : DetailsFragmentArgs by navArgs()
    private lateinit var recyclerViewActors : RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
        val actorsCount : TextView = view.findViewById(R.id.actorsCount)
        var ageLimit : String?
        detailsFragmentViewModel.movieById.observe(viewLifecycleOwner){ movieById->
            Glide.with(view).load(movieById.logoUrl).into(logo)
            if (movieById.coverUrl == null){
                Glide.with(view).load(movieById.posterUrl).into(cover)
            }
            else{
                Glide.with(view).load(movieById.coverUrl).into(cover)
            }
            if (movieById.ratingKinopoisk < 5)
            {
                rating.setTextColor(Color.parseColor("#FFAD0000"))
            }
            else if (movieById.ratingKinopoisk < 7){
                rating.setTextColor(Color.parseColor("#FFAFAFAF"))
            }
            nameOriginal.text = movieById.nameOriginal
            rating.text = movieById.ratingKinopoisk.toString()
            ageLimit = movieById.ratingAgeLimits?.filter { it.isDigit() }
            ratingAgeLimits.text = ageLimit + "+"
            duration.text = movieById.filmLength.toString() + "мин, "
            year.text = movieById.year.toString()
            country.text = movieById.countries[0].country
            genresDetail.text = movieById.genres[0].genre
            description.text = movieById.description
            actorsCount.text
            (activity as AppCompatActivity).supportActionBar?.title = movieById.nameRu
        }

        actorsViewModel.actors.observe(viewLifecycleOwner){
            actorAdapter.setActors(it)
            actorsCount.text = it.size.toString()
        }
        recyclerViewActors.adapter = actorAdapter
        recyclerViewActors.layoutManager = StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.HORIZONTAL)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (detailsFragmentViewModel.movieById == null) {
            val intent = Intent(activity, DetailsFragment::class.java)
            startActivity(intent)
        }
        detailsFragmentViewModel.getMovieById(detailsArgs.filmId)
        actorsViewModel.getStaff(detailsArgs.filmId)
    }
}
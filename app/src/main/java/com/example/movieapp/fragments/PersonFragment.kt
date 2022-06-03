package com.example.movieapp.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.adapter.MovieByIdAdapter
import com.example.movieapp.viewmodels.actorFragment.ActorFragmentViewModel

class PersonFragment : Fragment(R.layout.person_details) {

    private lateinit var recyclerActorFilms : RecyclerView
    private var moviesAdapter = MovieByIdAdapter()
    private val actorViewModel : ActorFragmentViewModel by viewModels()
    private val actorArgs : PersonFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerActorFilms = view.findViewById(R.id.actorFilms)
        val aboutPersonBtn : Button = view.findViewById(R.id.aboutPersonBtn)
        val actorPhoto : ImageView = view.findViewById(R.id.actorDetailsIV)
        val actorName : TextView = view.findViewById(R.id.actorDetailsNameTV)
        val actorNameOriginal : TextView = view.findViewById(R.id.actorDetailsNameOriginalTV)
        val actorProfession : TextView = view.findViewById(R.id.actorsDetailsProfessionTV)
        actorViewModel.actorById.observe(viewLifecycleOwner){   actor->
            Glide.with(view).load(actor.posterUrl).into(actorPhoto)
            actorName.text = actor.nameRu
            actorNameOriginal.text = actor.nameEn
            actorProfession.text = actor.profession
        }
        actorViewModel.filmsById.observe(viewLifecycleOwner){ films->
            moviesAdapter.setSeries(films)
        }
        aboutPersonBtn.setOnClickListener {

        }
        recyclerActorFilms.adapter = moviesAdapter
        recyclerActorFilms.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        actorViewModel.getActorById(actorArgs.actorId)
        actorViewModel.getMovies(actorArgs.actorId)
    }
}
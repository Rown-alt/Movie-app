package com.example.movieapp.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.viewmodels.ActorFragment.ActorFragmentViewModel

class ActorFragment : Fragment(R.layout.actor_details) {

    private val actorViewModel : ActorFragmentViewModel by viewModels()
    private val actorArgs : ActorFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val actorPhoto : ImageView = view.findViewById(R.id.actorDetailsIV)
        val actorName : TextView = view.findViewById(R.id.actorDetailsNameTV)
        val actorNameOriginal : TextView = view.findViewById(R.id.actorDetailsNameOriginalTV)
        val actorProfession : TextView = view.findViewById(R.id.actorsDetailsProfessionTV)

        actorViewModel.actorById.observe(viewLifecycleOwner){   actor->
            Glide.with(view).load(actor.posterUrl).into(actorPhoto)
            actorName.text = actor.nameRu
            actorNameOriginal.text = actor.nameEn
            actorProfession.text = actor.description
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        actorViewModel.getActorById(actorArgs.actorId)
    }
}
package com.example.movieapp.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.movieapp.R
import com.example.movieapp.viewmodels.actorFragment.ActorFragmentViewModel

class PersonDetailsFragment : Fragment(R.layout.facts_person) {
    private val actorId : PersonDetailsFragmentArgs by navArgs()

    private val personViewModel : ActorFragmentViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factsPerson : TextView = view.findViewById(R.id.factsPerson)
        val spousesPerson : TextView = view.findViewById(R.id.spousesPerson)
        val birthplacePerson : TextView = view.findViewById(R.id.birthplacePerson)
        var facts : String = ""
        var spouses : String = ""

        personViewModel.actorById.observe(viewLifecycleOwner){ actor->
            for (i in actor.facts){
                facts += i
                facts += "\n"
            }
            factsPerson.text = facts
//            for (i in actor.spouses){
//            }
//            spousesPerson.text = actor.spouses.toString()
            birthplacePerson.text = actor.birthplace
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        personViewModel.getActorById(actorId.actorId)
    }
}
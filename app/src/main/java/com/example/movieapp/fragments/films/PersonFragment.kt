package com.example.movieapp.fragments.films

import android.content.Context
import androidx.navigation.fragment.navArgs
import com.example.movieapp.fragments.abstractions.PersonAbstractFragment

class PersonFragment: PersonAbstractFragment(){
    private val actor : PersonFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        setActorId(actor.actorId)
    }
}
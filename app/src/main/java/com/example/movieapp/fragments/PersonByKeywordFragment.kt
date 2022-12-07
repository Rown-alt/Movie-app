package com.example.movieapp.fragments

import android.content.Context
import androidx.navigation.fragment.navArgs
import com.example.movieapp.fragments.abstractions.PersonAbstractFragment

class PersonByKeywordFragment: PersonAbstractFragment() {
    private val actor : PersonByKeywordFragmentArgs by navArgs()


    override fun onAttach(context: Context) {
        super.onAttach(context)
        setActorId(actor.userId)
    }
}
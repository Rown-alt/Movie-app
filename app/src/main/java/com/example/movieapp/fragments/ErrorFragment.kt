package com.example.movieapp.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.movieapp.R

class ErrorFragment : Fragment(R.layout.error_screen) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var exceptionName = arguments?.getString("exceptionName", "Something went wrong")
        var exceptionNameTV : TextView = view.findViewById(R.id.exceptionName)
        exceptionNameTV.text = exceptionName
    }
}
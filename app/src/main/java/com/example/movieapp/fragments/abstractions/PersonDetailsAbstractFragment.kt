package com.example.movieapp.fragments.abstractions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.movieapp.databinding.FactsPersonBinding

abstract class PersonDetailsAbstractFragment: Fragment() {
    private var _binding: FactsPersonBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FactsPersonBinding.inflate(inflater, container, false)
        return binding.root
    }
}
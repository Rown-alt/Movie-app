package com.example.movieapp.fragments.favourites

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.data.AppDatabase
import com.example.data.FilmDao
import com.example.movieapp.adapter.FavouritesAdapter
import com.example.movieapp.databinding.MyFilmsBinding
import com.example.movieapp.viewmodels.FavouritesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavouritesFragment: Fragment() {
    private var _binding: MyFilmsBinding? = null
    private val binding get() = _binding!!
    private val favouritesAdapter = FavouritesAdapter()
    private val favouritesViewModel: FavouritesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MyFilmsBinding.inflate(inflater, container, false)
        favouritesViewModel.getFilms()
        favouritesViewModel.films.observe(viewLifecycleOwner){
            favouritesAdapter.setMovies(it)
            Log.e("FilmsFavourite", it.toString())
        }
        binding.filmsRv.adapter = favouritesAdapter
        binding.filmsRv.layoutManager = GridLayoutManager(requireContext(), 2)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
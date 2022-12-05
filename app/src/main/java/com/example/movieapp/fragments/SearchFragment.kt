package com.example.movieapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.movieapp.adapter.FilmsByKeywordAdapter
import com.example.movieapp.adapter.MoviesAdapter
import com.example.movieapp.databinding.SearchFragmentBinding
import com.example.movieapp.viewmodels.searchFragment.SearchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {
    private var _binding: SearchFragmentBinding? = null
    private val binding get() = _binding!!
    private val searchViewModel : SearchViewModel by viewModel()
    private val filmAdapter = FilmsByKeywordAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SearchFragmentBinding.inflate(inflater, container, false)
        searchViewModel.search("Tenet")
        searchViewModel.movies.observe(viewLifecycleOwner){
            filmAdapter.setMovies(it)
        }
        binding.moviesRV.adapter = filmAdapter
        binding.moviesRV.layoutManager = StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.HORIZONTAL)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
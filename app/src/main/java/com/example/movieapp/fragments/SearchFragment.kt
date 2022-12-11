package com.example.movieapp.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.movieapp.adapter.FilmsByKeywordAdapter
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
        searchViewModel.movies.observe(viewLifecycleOwner){
            filmAdapter.setMovies(it)
            binding.filmsShimmer.stopShimmer()
            binding.filmsShimmer.visibility = View.GONE
            if (it.isEmpty()){
                binding.nullPlug.visibility = View.VISIBLE
            }
            else{
                binding.nullPlug.visibility = View.GONE
            }
        }
        binding.searchBtn.setOnClickListener {
            val searchPhrase = binding.searchView.text.toString()
            searchViewModel.search(searchPhrase)
            filmAdapter.deleteMovies()
            binding.nullPlug.visibility = View.GONE
            if (searchPhrase!=""){
                binding.filmsShimmer.visibility = View.VISIBLE
                binding.filmsShimmer.startShimmer()
            }
        }
        binding.searchView.setOnEditorActionListener { textView, _, _ ->
            val searchPhrase = textView.text.toString()
            searchViewModel.search(searchPhrase)
            filmAdapter.deleteMovies()
            binding.nullPlug.visibility = View.GONE
            if (searchPhrase!=""){
                binding.filmsShimmer.visibility = View.VISIBLE
                binding.filmsShimmer.startShimmer()
            }
            hideKeyboard(requireActivity(), textView)
            true
        }
        binding.moviesRV.adapter = filmAdapter
        binding.moviesRV.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun hideKeyboard(activity: Activity, view: View){
        val imm : InputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
        view.clearFocus()
    }
}
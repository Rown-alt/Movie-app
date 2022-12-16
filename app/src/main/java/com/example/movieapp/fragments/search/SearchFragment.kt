package com.example.movieapp.fragments.search

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.movieapp.adapter.CountriesAdapter
import com.example.movieapp.adapter.FilmsByKeywordAdapter
import com.example.movieapp.adapter.FilterAdapter
import com.example.movieapp.adapter.GenresAdapter
import com.example.movieapp.databinding.SearchFragmentBinding
import com.example.movieapp.viewmodels.searchFragment.SearchViewModel
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {
    private var _binding: SearchFragmentBinding? = null
    private val binding get() = _binding!!
    private val searchViewModel : SearchViewModel by viewModel()
    private val filmAdapter = FilmsByKeywordAdapter()
    private val genresAdapter = GenresAdapter()
    private val countriesAdapter = CountriesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (searchViewModel.filters.value?.genres.isNullOrEmpty()){
            Log.e("Filters", "here")
            searchViewModel.getFilters()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SearchFragmentBinding.inflate(inflater, container, false)
        observeViewModel()
        binding.searchBtn.setOnClickListener {
            val searchPhrase = binding.searchView.text.toString()
            searchViewModel.search(searchPhrase)
            filmAdapter.deleteMovies()
            binding.nullPlug.visibility = View.GONE
            binding.genresRv.visibility = View.GONE
            binding.countriesRv.visibility = View.GONE
            binding.countriesTitle.visibility = View.GONE
            binding.genresTitle.visibility = View.GONE
            if (searchPhrase != "") {
                binding.filmsShimmer.visibility = View.VISIBLE
                binding.filmsShimmer.startShimmer()
            }
            hideKeyboard(requireActivity(), requireView())
        }

        binding.searchView.setOnEditorActionListener { textView, _, _ ->
            val searchPhrase = textView.text.toString()
            searchViewModel.search(searchPhrase)
            filmAdapter.deleteMovies()
            binding.nullPlug.visibility = View.GONE
            binding.genresRv.visibility = View.GONE
            binding.countriesRv.visibility = View.GONE
            binding.countriesTitle.visibility = View.GONE
            binding.genresTitle.visibility = View.GONE
            if (searchPhrase!=""){
                binding.filmsShimmer.visibility = View.VISIBLE
                binding.filmsShimmer.startShimmer()
            }
            hideKeyboard(requireActivity(), textView)
            true
        }
        binding.searchView.setOnFocusChangeListener { view, b ->
            if (view.hasFocus()){
                binding.searchBackground.visibility = View.VISIBLE
            }
            else{
                binding.searchBackground.visibility = View.INVISIBLE
            }
        }

        binding.genresRv.adapter = genresAdapter
        val flexLayoutManager = FlexboxLayoutManager(requireContext())
        flexLayoutManager.justifyContent = JustifyContent.FLEX_START
        flexLayoutManager.flexDirection = FlexDirection.ROW
        binding.genresRv.layoutManager = flexLayoutManager

        binding.countriesRv.adapter = countriesAdapter
        val flexLayoutManager2 = FlexboxLayoutManager(requireContext())
        flexLayoutManager2.justifyContent = JustifyContent.FLEX_START
        flexLayoutManager2.flexDirection = FlexDirection.ROW
        binding.countriesRv.layoutManager = flexLayoutManager2

        binding.moviesRV.adapter = filmAdapter
        binding.moviesRV.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun observeViewModel(){
        searchViewModel.movies.observe(viewLifecycleOwner){
            filmAdapter.setMovies(it)
            binding.moviesRV.visibility = View.VISIBLE
            binding.filmsShimmer.stopShimmer()
            binding.filmsShimmer.visibility = View.GONE
            if (it.isEmpty()){
                binding.nullPlug.visibility = View.VISIBLE
            }
            else{
                binding.nullPlug.visibility = View.GONE
            }
        }
        searchViewModel.filters.observe(viewLifecycleOwner){
            countriesAdapter.setCountries(it.countries)
            genresAdapter.setGenres(it.genres)
        }
    }

    private fun hideKeyboard(activity: Activity, view: View){
        val imm : InputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
        view.clearFocus()
    }
}
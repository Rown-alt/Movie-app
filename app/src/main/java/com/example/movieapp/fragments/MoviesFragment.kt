package com.example.movieapp.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.movieapp.adapter.MovieAdapter
import com.example.movieapp.adapter.SeriesAdapter
import com.example.movieapp.viewmodels.premieres.MoviesScreenViewModel
import com.example.movieapp.viewmodels.series.SeriesViewModel

class MoviesFragment : Fragment(R.layout.movies_screen){
    private var movieAdapter = MovieAdapter()
    private lateinit var recyclerViewPremieres : RecyclerView
    private val viewModelPremieres : MoviesScreenViewModel by viewModels()

    private val viewModelSeries : SeriesViewModel by viewModels()
    private lateinit var recyclerViewSeries : RecyclerView
    private var seriesAdapter = SeriesAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerViewPremieres = view.findViewById(R.id.moviesRV)
        recyclerViewSeries = view.findViewById(R.id.seriesRV)
        viewModelPremieres.movies.observe(viewLifecycleOwner){
            movieAdapter.setMovies(it)
        }
        viewModelSeries.series.observe(viewLifecycleOwner){
            seriesAdapter.setSeries(it)
        }
        recyclerViewPremieres.adapter = movieAdapter
        recyclerViewPremieres.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
        recyclerViewSeries.adapter = seriesAdapter
        recyclerViewSeries.layoutManager = StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModelPremieres.getCharacters("MAY")
        viewModelSeries.addSeries(251568)
        viewModelSeries.addSeries(401522)
        viewModelSeries.addSeries(502838)
        viewModelSeries.getSeries()
    }
}
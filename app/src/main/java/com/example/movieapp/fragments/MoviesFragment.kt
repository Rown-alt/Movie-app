package com.example.movieapp.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.movieapp.adapter.PremieresAdapter
import com.example.movieapp.adapter.MovieByIdAdapter
import com.example.movieapp.adapter.TopAdapter
import com.example.movieapp.viewmodels.premieres.MoviesScreenViewModel
import com.example.movieapp.viewmodels.series.MovieByIdViewModel
import kotlin.random.Random

class MoviesFragment : Fragment(R.layout.movies_screen){
    private var movieAdapter = PremieresAdapter()
    private lateinit var recyclerViewPremieres : RecyclerView
    private val viewModelPremieres : MoviesScreenViewModel by viewModels()

    private val viewModelSeries : MovieByIdViewModel by viewModels()
    private lateinit var recyclerViewSeries : RecyclerView
    private var seriesAdapter = MovieByIdAdapter()

    private lateinit var recyclerViewTop : RecyclerView
    private var topAdapter = TopAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerViewPremieres = view.findViewById(R.id.moviesRV)
        recyclerViewSeries = view.findViewById(R.id.seriesRV)
        recyclerViewTop = view.findViewById(R.id.topRV)
        viewModelPremieres.exception.observe(viewLifecycleOwner){
            if (it!=null){
                var bundle = Bundle()
                bundle.putString("exceptionName", it.toString())
                bundle.putString("fragmentName", "MoviesFragment")
                val errorFragment = ErrorFragment()
                errorFragment.arguments = bundle
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.navHostFragment, errorFragment, "fragmentId")
                    ?.commit()

            }
        }
        viewModelPremieres.movies.observe(viewLifecycleOwner){
            movieAdapter.setMovies(it)
        }
        viewModelPremieres.top.observe(viewLifecycleOwner){
            topAdapter.setTop(it)
        }
        viewModelSeries.series.observe(viewLifecycleOwner){
            seriesAdapter.setSeries(it)
        }

        recyclerViewPremieres.adapter = movieAdapter
        recyclerViewPremieres.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)

        recyclerViewSeries.adapter = seriesAdapter
        recyclerViewSeries.layoutManager = StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL)

        recyclerViewTop.adapter = topAdapter
        recyclerViewTop.layoutManager = StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModelPremieres.getPremieres(Random.nextInt(1995, 2021),"MAY")
        viewModelPremieres.getTop("TOP_250_BEST_FILMS")
        viewModelSeries.getSeries()
    }
}
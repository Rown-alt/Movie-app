package com.example.movieapp.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.movieapp.adapter.MoviesAdapter
import com.example.movieapp.adapter.MovieByIdAdapter
import com.example.movieapp.adapter.TopAdapter
import com.example.movieapp.viewmodels.moviesFragment.MoviesScreenViemModelFactory
import com.example.movieapp.viewmodels.moviesFragment.MoviesScreenViewModel
import com.example.movieapp.viewmodels.series.MovieByIdViewModel
import com.example.movieapp.viewmodels.series.MovieByIdViewModelFactory
import kotlin.random.Random

class MoviesFragment : Fragment(R.layout.movies_screen){
    private var movieAdapter = MoviesAdapter()
    private lateinit var recyclerViewPremieres : RecyclerView
    private val viewModelPremieres : MoviesScreenViewModel by activityViewModels{MoviesScreenViemModelFactory()}
    private val viewModelSeries : MovieByIdViewModel by activityViewModels{ MovieByIdViewModelFactory() }
    private lateinit var recyclerViewSeries : RecyclerView
    private var seriesAdapter = MovieByIdAdapter()

    private lateinit var recyclerViewTop : RecyclerView
    private var topAdapter = TopAdapter()

    private lateinit var randomFilmIV : ImageView
    private lateinit var randomFilmName : TextView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerViewPremieres = view.findViewById(R.id.moviesRV)
        recyclerViewSeries = view.findViewById(R.id.seriesRV)
        recyclerViewTop = view.findViewById(R.id.topRV)

        randomFilmName = view.findViewById(R.id.randomFilmName)
        randomFilmIV = view.findViewById(R.id.randomFilmIV)

        var randomFilmId = 7

        viewModelPremieres.exception.observe(viewLifecycleOwner){
            if (it!=null){
                val bundle = Bundle()
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
        viewModelPremieres.randomFilm.observe(viewLifecycleOwner) {
            randomFilmName.text = it.nameRu
            Glide.with(view).load(it.posterUrl).into(randomFilmIV)
            randomFilmId = it.filmId
        }
        randomFilmIV.setOnClickListener {
            val action = MoviesFragmentDirections.actionMoviesFragmentToDetailsFragment(randomFilmId)
            it.findNavController().navigate(action)
            Log.e("AAA", "Data sent")
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
        Log.e("ABC", "requests sending")

        if (viewModelSeries.series.value == null) {
            viewModelSeries.getSeries()
        }
        if (viewModelPremieres.randomFilm.value == null){
            viewModelPremieres.getRandomFilm()
        }

        if (viewModelPremieres.top.value == null){
            viewModelPremieres.getTop("TOP_250_BEST_FILMS")
        }
        if (viewModelPremieres.movies.value == null){
            viewModelPremieres.getPremieres(Random.nextInt(1995, 2021),"MAY")
        }
        //
    }
}
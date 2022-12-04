package com.example.movieapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.movieapp.R
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.movieapp.adapter.MoviesAdapter
import com.example.movieapp.adapter.MovieByIdAdapter
import com.example.movieapp.adapter.TopAdapter
import com.example.movieapp.databinding.MoviesScreenBinding
import com.example.movieapp.viewmodels.moviesFragment.MoviesScreenViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.random.Random

class MoviesFragment : Fragment(R.layout.movies_screen){
    private var _binding: MoviesScreenBinding? = null
    private val binding get() = _binding!!
    private var movieAdapter = MoviesAdapter()
    private var seriesAdapter = MovieByIdAdapter()
    private var topAdapter = TopAdapter()
    private val moviesViewModel : MoviesScreenViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        moviesViewModel.getSeries()
//        val page = Random.nextInt(1, 8)
//        moviesViewModel.getTop("TOP_250_BEST_FILMS", page)
//        moviesViewModel.getPremieres(Random.nextInt(1995, 2021),"MAY")
//        moviesViewModel.getRandomFilm()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MoviesScreenBinding.inflate(inflater, container, false)
        binding.topRVShimmer.startShimmer()
        binding.moviesRVShimmer.startShimmer()
        binding.seriesRVShimmer.startShimmer()
        var randomFilmId = 7
        moviesViewModel.exception.observe(viewLifecycleOwner){
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
        moviesViewModel.movies.observe(viewLifecycleOwner){
            movieAdapter.setMovies(it)
            binding.moviesRVShimmer.stopShimmer()
            binding.moviesRVShimmer.visibility = View.GONE
        }
        moviesViewModel.topMovies.observe(viewLifecycleOwner){
            topAdapter.setTop(it)
            binding.topRVShimmer.stopShimmer()
            binding.topRVShimmer.visibility = View.GONE
        }
        moviesViewModel.series.observe(viewLifecycleOwner){
            seriesAdapter.setSeries(it)
            binding.seriesRVShimmer.stopShimmer()
            binding.seriesRVShimmer.visibility = View.GONE
        }
        moviesViewModel.randomFilm.observe(viewLifecycleOwner) {
            binding.randomFilmName.text = it.nameRu
            Glide.with(requireView()).load(it.posterUrl).into(binding.randomFilmIV)
            randomFilmId = it.filmId
        }

        binding.randomFilmIV.setOnClickListener {
            val action = MoviesFragmentDirections.actionMoviesFragmentToDetailsFragment(randomFilmId)
            it.findNavController().navigate(action)
            Log.e("AAA", "Data sent")
        }

        binding.moviesRV.adapter = movieAdapter
        binding.moviesRV.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)

        binding.seriesRV.adapter = seriesAdapter
        binding.seriesRV.layoutManager = StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL)

        binding.topRV.adapter = topAdapter
        binding.topRV.layoutManager = StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
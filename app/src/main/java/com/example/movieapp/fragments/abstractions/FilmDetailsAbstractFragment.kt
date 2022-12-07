package com.example.movieapp.fragments.abstractions

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.adapter.ActorsAdapter
import com.example.movieapp.adapter.SimilarsAdapter
import com.example.movieapp.databinding.DetailsScreenBinding
import com.example.movieapp.fragments.DetailsFragmentArgs
import com.example.movieapp.fragments.ErrorFragment
import com.example.movieapp.models.MovieById
import com.example.movieapp.viewmodels.detailsFragment.DetailsFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

abstract class FilmDetailsAbstractFragment: Fragment() {
    private var _binding : DetailsScreenBinding? = null
    private val binding get() = _binding!!
    private val detailsFragmentViewModel : DetailsFragmentViewModel by viewModel()

    protected var actorAdapter = ActorsAdapter()
    private var similarsAdapter = SimilarsAdapter()
    private var _filmId = 0

    protected fun setFilmId(filmId: Int){
        _filmId = filmId
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailsFragmentViewModel.getMovieById(_filmId)
        detailsFragmentViewModel.getStaff(_filmId)
        detailsFragmentViewModel.getSimilars(_filmId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailsScreenBinding.inflate(inflater, container, false)
        detailsFragmentViewModel.exception.observe(viewLifecycleOwner){
            if (it!=null){
                val bundle = Bundle()
                bundle.putString("exceptionName", it.toString())
                bundle.putString("fragmentName", "DetailsFragment")
                //bundle.putInt("FilmId", )
                val errorFragment = ErrorFragment()
                errorFragment.arguments = bundle
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.navHostFragment, errorFragment, "fragmentId")
                    ?.commit()
            }
        }
        detailsFragmentViewModel.movieById.observe(viewLifecycleOwner){ movieById->
            setMovieLayout(movieById, requireView())
        }

        detailsFragmentViewModel.similarFilms.observe(viewLifecycleOwner){
            similarsAdapter.setSimilars(it)
        }

        detailsFragmentViewModel.actors.observe(viewLifecycleOwner){
            actorAdapter.setActors(it)
            binding.actorsCount.text = it.size.toString()
        }

        binding.similarFilmsRV.adapter = similarsAdapter
        binding.similarFilmsRV.layoutManager = StaggeredGridLayoutManager(1,
            StaggeredGridLayoutManager.HORIZONTAL)

        binding.actorsRV.adapter = actorAdapter
        binding.actorsRV.layoutManager = StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.HORIZONTAL)
        return binding.root
    }

    private fun setMovieLayout(movie: MovieById, view: View){
        Glide.with(view).load(movie.logoUrl).into(binding.logo)
        if (movie.coverUrl == null){
            Glide.with(view).load(movie.posterUrl).into(binding.cover)
        }
        else{
            Glide.with(view).load(movie.coverUrl).into(binding.cover)
        }
        if (movie.ratingKinopoisk < 5)
        {
            binding.rating.setTextColor(Color.parseColor("#FFAD0000"))
        }
        else if (movie.ratingKinopoisk < 7){
            binding.rating.setTextColor(Color.parseColor("#FFAFAFAF"))
        }

        binding.nameOriginal.text = movie.nameOriginal
        binding.rating.text = movie.ratingKinopoisk.toString()
        val ageLimit = movie.ratingAgeLimits?.filter { it.isDigit() }
        binding.ratingAgeLimits.text = "$ageLimit+"
        binding.duration.text = movie.filmLength.toString() + "мин, "
        binding.year.text = movie.year.toString()
        binding.country.text = movie.countries[0].country
        binding.genresDetails.text = movie.genres[0].genre
        binding.description.text = movie.description
        binding.actorsCount.text

        (activity as AppCompatActivity).supportActionBar?.title = movie.nameRu
    }

}
package com.example.movieapp.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.databinding.FilmBinding
import com.example.movieapp.fragments.MoviesFragmentDirections
import com.example.movieapp.models.top_of_films.FilmsTop
import com.example.movieapp.models.top_of_films.TopOfFilms

class TopAdapter :RecyclerView.Adapter<TopAdapter.FilmViewHolder>() {
    private var filmList = ArrayList<FilmsTop>()

    class FilmViewHolder(binding: FilmBinding) : RecyclerView.ViewHolder(binding.root) {
        private var genres : String? = null
        private val image : ImageView = binding.picture
        private val name : TextView = binding.name
        private val genre : TextView = binding.genre
        private val rating : TextView = binding.filmRating

        fun bind(films: FilmsTop){
            rating.text = films.rating
            when {
                films.rating.toDouble() < 5.0 -> {
                    rating.setBackgroundColor(Color.parseColor("#FFAD0000"))
                }
                films.rating.toDouble() < 7.0 -> {
                    rating.setBackgroundColor(Color.parseColor("#FFAFAFAF"))
                }
                else -> {
                    rating.setBackgroundColor(Color.parseColor("#FF52D050"))
                }
            }
            genres = films.genres[0].genre
            name.text = films.nameRu
            Glide.with(itemView).load(films.posterUrlPreview).into(image)
            genre.text = genres
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FilmViewHolder {
        val binding = FilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilmViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.bind(filmList[position])

        holder.itemView.setOnClickListener{
            val action = MoviesFragmentDirections.actionMoviesFragmentToDetailsFragment(filmList[position].filmId)
            it.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return filmList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setTop(topOfFilms : ArrayList<FilmsTop>){
        this.filmList = topOfFilms
        notifyDataSetChanged()
    }
}
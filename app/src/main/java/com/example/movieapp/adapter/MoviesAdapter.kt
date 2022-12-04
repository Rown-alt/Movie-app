package com.example.movieapp.adapter

import android.annotation.SuppressLint
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
import com.example.movieapp.models.Movie

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {
    private var listMovie : ArrayList<Movie> = arrayListOf()

    class MovieViewHolder(view: FilmBinding) : RecyclerView.ViewHolder(view.root){
        private var genres : String? = null
        private val image : ImageView = view.picture
        private val name : TextView = view.name
        private val genre : TextView = view.genre
        fun bind (movie : Movie){
            genres = movie.genres[0].genre
            name.text = movie.nameRu
            genre.text = genres
            Glide.with(itemView).load(movie.posterUrlPreview).into(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = FilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(listMovie[position])

        holder.itemView.setOnClickListener{
            val action = MoviesFragmentDirections.actionMoviesFragmentToDetailsFragment(listMovie[position].kinopoiskId)
            it.findNavController().navigate(action)
            Log.e("AAA", "Data sent to DetailsFragment")
        }
    }

    override fun getItemCount(): Int {
        return listMovie.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setMovies(movies : ArrayList<Movie>){
        this.listMovie = movies
        notifyDataSetChanged()
    }
}
package com.example.movieapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.data.Film
import com.example.movieapp.databinding.FavouriteFilmBinding
import com.example.movieapp.databinding.FilmBinding

class FavouritesAdapter : RecyclerView.Adapter<FavouritesAdapter.MovieViewHolder>() {
    private var listFilms: List<Film> = listOf()

    class MovieViewHolder(view: FavouriteFilmBinding) : RecyclerView.ViewHolder(view.root) {
        private val image: ImageView = view.picture
        private val name: TextView = view.name

        @SuppressLint("SetTextI18n")
        fun bind(film: Film) {
            Glide.with(itemView).load(film.logo).into(image)
            name.text = film.filmName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = FavouriteFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(listFilms[position])

    }

    override fun getItemCount(): Int {
        return listFilms.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setMovies(films: List<Film>) {
        this.listFilms = films
        notifyDataSetChanged()
    }
}
package com.example.movieapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.databinding.FilmBinding
import com.example.movieapp.models.films_by_keyword.FilmByKeyword

class FilmsByKeywordAdapter : RecyclerView.Adapter<FilmsByKeywordAdapter.MovieViewHolder>() {
    private var listMovie: ArrayList<FilmByKeyword> = arrayListOf()

    class MovieViewHolder(view: FilmBinding) : RecyclerView.ViewHolder(view.root) {
        private var genres: String? = null
        private val image: ImageView = view.picture
        private val name: TextView = view.name
        private val genre: TextView = view.genre
        fun bind(film: FilmByKeyword) {
            genres = film.genres[0].genre
            name.text = film.nameRu
            genre.text = genres
            Glide.with(itemView).load(film.posterUrlPreview).into(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = FilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(listMovie[position])
    }

    override fun getItemCount(): Int {
        return listMovie.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setMovies(movies: ArrayList<FilmByKeyword>) {
        this.listMovie = movies
        notifyDataSetChanged()
    }
}

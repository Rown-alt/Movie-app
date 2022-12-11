package com.example.movieapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.databinding.FilmByKeywordBinding
import com.example.movieapp.fragments.SearchFragmentDirections
import com.example.movieapp.models.films_by_keyword.FilmByKeyword

class FilmsByKeywordAdapter : RecyclerView.Adapter<FilmsByKeywordAdapter.MovieViewHolder>() {
    private var listMovie: ArrayList<FilmByKeyword> = arrayListOf()

    class MovieViewHolder(view: FilmByKeywordBinding) : RecyclerView.ViewHolder(view.root) {
        private var genres: String? = null
        private val image: ImageView = view.picture
        private val name: TextView = view.name
        private val genre: TextView = view.genres
        private val description: TextView = view.description
        private val filmLength: TextView = view.filmLength

        @SuppressLint("SetTextI18n")
        fun bind(film: FilmByKeyword) {
            if (film.genres.isNotEmpty()){
                val filmGenre = film.genres.iterator()
                while (filmGenre.hasNext()){
                    genre.text = "${genre.text}${filmGenre.next().genre}, "
                }
            }
            name.text = film.nameRu
            Glide.with(itemView).load(film.posterUrlPreview).into(image)
            description.text = film.description
            filmLength.text = "${film.filmLength} мин"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = FilmByKeywordBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(listMovie[position])

        holder.itemView.setOnClickListener {
            val action = SearchFragmentDirections.actionSearchFragmentToFilmByKeyWordDetails(listMovie[position].filmId!!)
            it.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return listMovie.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setMovies(movies: ArrayList<FilmByKeyword>) {
        this.listMovie = movies
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun deleteMovies(){
        this.listMovie = arrayListOf()
        notifyDataSetChanged()
    }
}

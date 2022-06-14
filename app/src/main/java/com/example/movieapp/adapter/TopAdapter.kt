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
import com.example.movieapp.fragments.MoviesFragmentDirections
import com.example.movieapp.models.top_of_films.FilmsTop
import com.example.movieapp.models.top_of_films.TopOfFilms

class TopAdapter : RecyclerView.Adapter<TopAdapter.FilmViewHolder>() {

    private var filmList = ArrayList<FilmsTop>()

    class FilmViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        private var genres : String? = null

        private val image : ImageView = view.findViewById(R.id.picture)
        private val name : TextView = view.findViewById(R.id.name)
        private val genre : TextView = view.findViewById(R.id.genre)
        private val rating : TextView = view.findViewById(R.id.filmRating)
        fun bind(films: FilmsTop){
            rating.text = films.rating
            if (films.rating == null){
                rating.text = "-"
            }
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
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.film, parent, false)
        return FilmViewHolder(view)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.bind(filmList[position])

        holder.itemView.setOnClickListener{
            val action = MoviesFragmentDirections.actionMoviesFragmentToDetailsFragment(filmList[position].filmId)
            it.findNavController().navigate(action)
            Log.e("Usage of data", "Data sent")
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
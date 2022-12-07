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
import com.example.movieapp.models.MovieById

class MovieByIdAdapter : RecyclerView.Adapter<MovieByIdAdapter.SeriesViewHolder>() {
    private var seriesList = ArrayList<MovieById>()

    class SeriesViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        private var genres : String? = null

        private val image : ImageView = view.findViewById(R.id.picture)
        private val name : TextView = view.findViewById(R.id.name)
        private val genre : TextView = view.findViewById(R.id.genre)
        private val rating : TextView = view.findViewById(R.id.filmRating)
        fun bind(series : MovieById){
            rating.text = series.ratingKinopoisk.toString()
            when {
                series.ratingKinopoisk < 5 -> {
                    rating.setBackgroundColor(Color.parseColor("#FFAD0000"))
                }
                series.ratingKinopoisk < 7.0 -> {
                    rating.setBackgroundColor(Color.parseColor("#FFAFAFAF"))
                }
                else -> {
                    rating.setBackgroundColor(Color.parseColor("#FF52D050"))
                }
            }
            genres = series.genres[0].genre
            name.text = series.nameRu
            Glide.with(itemView).load(series.posterUrlPreview).into(image)
            genre.text = genres
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.film, parent, false)
        return SeriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        holder.bind(seriesList[position])
        Log.e("AAA","a")
        holder.itemView.setOnClickListener{
            val action = MoviesFragmentDirections.actionMoviesFragmentToDetailsFragment(seriesList[position].kinopoiskId)
            it.findNavController().navigate(action)
            Log.e("AAA", "Data sent")
        }
    }

    override fun getItemCount(): Int {
        return seriesList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setSeries(series: ArrayList<MovieById>){
        this.seriesList = series
        notifyDataSetChanged()
    }
}
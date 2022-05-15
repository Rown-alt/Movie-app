package com.example.movieapp.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.models.MovieById

class SeriesAdapter : RecyclerView.Adapter<SeriesAdapter.SeriesViewHolder>() {

    private var seriesList = ArrayList<MovieById>()

    class SeriesViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        private var genres : String? = null

        private val image : ImageView = view.findViewById(R.id.picture)
        private val name : TextView = view.findViewById(R.id.name)
        private val genre : TextView = view.findViewById(R.id.genre)

        fun bind(series : MovieById){
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
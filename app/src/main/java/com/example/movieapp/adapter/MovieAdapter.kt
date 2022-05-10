package com.example.movieapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.models.Movie

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var listMovie : List<Movie> = listOf()
    class MovieViewHolder(view : View) : RecyclerView.ViewHolder(view){
        private val image : ImageView = view.findViewById(R.id.picture)
        private val name : TextView = view.findViewById(R.id.name)
        private val duration : TextView = view.findViewById(R.id.duration)
        private val star_1 : ImageView = view.findViewById(R.id.star_1)
        private val star_2 : ImageView = view.findViewById(R.id.star_2)
        private val star_3 : ImageView = view.findViewById(R.id.star_3)
        private val star_4 : ImageView = view.findViewById(R.id.star_4)
        private val star_5 : ImageView = view.findViewById(R.id.star_5)
        private val genre : TextView = view.findViewById(R.id.genre)
        private val reviews : TextView = view.findViewById(R.id.reviews)
        private val year_rating : TextView = view.findViewById(R.id.year_rating)
        private val like : ImageView = view.findViewById(R.id.like)

        fun bind (movie : Movie){
            name.text = movie.name
            duration.text = movie.duration
            genre.text = movie.genre
            reviews.text = movie.reviews
            year_rating.text = movie.year_rating

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movies_screen,parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(listMovie[position])

        holder.itemView.setOnClickListener{

        }
    }

    override fun getItemCount(): Int {
        return listMovie.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setMovies(movies : List<Movie>){
        this.listMovie = movies
        notifyDataSetChanged()
    }
}
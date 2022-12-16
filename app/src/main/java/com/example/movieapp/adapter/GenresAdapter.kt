package com.example.movieapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.FilterBinding
import com.example.movieapp.models.FiltersRequest

class GenresAdapter: RecyclerView.Adapter<GenresAdapter.FilterViewHolder>() {
    private var filters: List<FiltersRequest.Genre> = listOf()

    class FilterViewHolder(binding: FilterBinding): RecyclerView.ViewHolder(binding.root) {
        private val title: TextView = binding.filterTitle
        fun bindGenres(genre: FiltersRequest.Genre){
            title.text = genre.genre
            title.isSelected = genre.state
            title.setOnClickListener {
                genre.state = !genre.state
                title.isSelected = genre.state
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        val view =  FilterBinding.inflate(LayoutInflater.from(parent.context))
        return FilterViewHolder(view)
    }

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        holder.bindGenres(filters[position])
    }

    override fun getItemCount(): Int {
        return filters.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setGenres(filters: List<FiltersRequest.Genre>){
        this.filters = filters
        if (filters.size>=6){
            this.filters = this.filters.subList(0, 6)
        }
        notifyDataSetChanged()
    }
}
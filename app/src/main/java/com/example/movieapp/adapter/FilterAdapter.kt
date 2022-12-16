package com.example.movieapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.FilterBinding
import com.example.movieapp.models.FiltersRequest

class FilterAdapter: RecyclerView.Adapter<FilterAdapter.FilterViewHolder>() {
    private var filters: FiltersRequest? = null

    class FilterViewHolder(binding: FilterBinding): RecyclerView.ViewHolder(binding.root) {
        private val title: TextView = binding.filterTitle
        fun bindGenres(genre: FiltersRequest.Genre){
            title.text = genre.genre
            title.setOnClickListener {
                title.isSelected = !title.isSelected
            }
        }
        fun bindCounties(country: FiltersRequest.Country){
            title.text = country.country
            title.setOnClickListener {
                title.isSelected = !title.isSelected
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        val view =  FilterBinding.inflate(LayoutInflater.from(parent.context))
        return FilterViewHolder(view)
    }

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        holder.bindGenres(filters!!.genres[position])
        holder.bindCounties(filters!!.countries[position])
    }

    override fun getItemCount(): Int {
        return (filters!!.countries.size + filters!!.genres.size)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setFilters(filters: FiltersRequest){
        this.filters = filters
        notifyDataSetChanged()
    }
}
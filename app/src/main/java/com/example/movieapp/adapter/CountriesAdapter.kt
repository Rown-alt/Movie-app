package com.example.movieapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.FilterBinding
import com.example.movieapp.models.FiltersRequest

class CountriesAdapter: RecyclerView.Adapter<CountriesAdapter.FilterViewHolder>() {
    private var filters: List<FiltersRequest.Country> = listOf()

    class FilterViewHolder(binding: FilterBinding): RecyclerView.ViewHolder(binding.root) {
        private val title: TextView = binding.filterTitle
        fun bindCounties(country: FiltersRequest.Country){
            title.text = country.country
            title.isSelected = country.state
            title.setOnClickListener {
                country.state = !country.state
                title.isSelected = !title.isSelected
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        val view =  FilterBinding.inflate(LayoutInflater.from(parent.context))
        return FilterViewHolder(view)
    }

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        holder.bindCounties(filters[position])
    }

    override fun getItemCount(): Int {
        return filters.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setCountries(filters: List<FiltersRequest.Country>){
        this.filters = filters
        if (filters.size>=6){
            this.filters = this.filters.subList(0, 6)
        }
        notifyDataSetChanged()
    }
}

package com.example.movieapp.adapter

import android.annotation.SuppressLint
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
import com.example.movieapp.fragments.films.DetailsFragmentDirections

import com.example.movieapp.models.similars.Similar

class SimilarsAdapter : RecyclerView.Adapter<SimilarsAdapter.SimilarViewHolder>() {
    private var listSimilars : ArrayList<Similar> = arrayListOf()

    class SimilarViewHolder(view : View) : RecyclerView.ViewHolder(view){

        private val image : ImageView = view.findViewById(R.id.picture)
        private val name : TextView = view.findViewById(R.id.name)
        fun bind (similar : Similar){
            name.text = similar.nameRu
            Glide.with(itemView).load(similar.posterUrlPreview).into(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.film,parent, false)
        return SimilarViewHolder(view)
    }

    override fun onBindViewHolder(holder: SimilarViewHolder, position: Int) {
        holder.bind(listSimilars[position])

        //Сломался поиск по id

//        holder.itemView.setOnClickListener{
//            val action = DetailsFragmentDirections.actionDetailsFragmentSelf(listSimilars[position].kinopoiskId)
//            it.findNavController().navigate(action)
//            Log.e("AAA", "Data sent to DetailsFragment")
//        }
    }

    override fun getItemCount(): Int {
        return listSimilars.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setSimilars(similars : ArrayList<Similar>){
        this.listSimilars = similars
        notifyDataSetChanged()
    }
}
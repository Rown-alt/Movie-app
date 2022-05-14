package com.example.movieapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.models.Person

class ActorsAdapter : RecyclerView.Adapter<ActorsAdapter.ActorViewHolder>() {
    var staff : List<Person> = listOf()
    class ActorViewHolder(view : View) : RecyclerView.ViewHolder(view){
        private val actorName : TextView = view.findViewById(R.id.actorName)
        private val actorRole : TextView = view.findViewById(R.id.actorRole)
        private val actorPicture : ImageView = view.findViewById(R.id.actorPicture)

        fun bind(actor : Person){
            actorName.text = actor.nameRu
            actorRole.text = actor.description
            Glide.with(itemView).load(actor.posterUrl).into(actorPicture)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.actor, parent, false)
        return ActorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.bind(staff[position])
    }

    override fun getItemCount(): Int {
        return staff.size
    }
}
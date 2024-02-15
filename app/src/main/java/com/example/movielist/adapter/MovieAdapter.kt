package com.example.movielist.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movielist.R
import com.example.movielist.model.ResultsItem

class MovieAdapter(
    private val context: Context,
    private val movies: List<ResultsItem>
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val ivTv: ImageView
        val tvTitleTv: TextView
        val tvDescTv: TextView
        val ratingTv: RatingBar
        val tvRating: TextView

        init {
            ivTv = view.findViewById(R.id.ivTv)
            tvTitleTv = view.findViewById(R.id.tvTitleTv)
            tvDescTv = view.findViewById(R.id.tvDescTv)
            ratingTv = view.findViewById(R.id.ratingTv)
            tvRating = view.findViewById(R.id.tvRating)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]

        holder.tvTitleTv.text = movie.title
        holder.tvDescTv.text = movie.overview

        val baseUrlImage = "https://image.tmdb.org/t/p/w780"
        val urlImage = baseUrlImage + movie.backdropPath

        Glide.with(context).load(urlImage).into(holder.ivTv)

        val rating = movie.voteAverage?.div(2)
        if (rating != null) {
            holder.ratingTv.rating = rating.toFloat()
        }

        holder.tvRating.text = rating.toString()

        holder.itemView.setOnClickListener {
//            val intent = Intent(context, DetailMovieActivity::class.java)
//            intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, movie)
//            context.startActivity(intent)
        }
    }
}
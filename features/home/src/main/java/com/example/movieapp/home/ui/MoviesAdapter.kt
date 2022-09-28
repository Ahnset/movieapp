package com.example.movieapp.home.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.home.R
import com.example.movieapp.home.databinding.ItemMovieBinding
import com.example.movieapp.presentation.common.GlideHelper.setAsyncImage
import com.example.movieapp.presentation.common.StringHelper.normalize
import com.example.movieapp.presentation.model.MovieUI

class MoviesAdapter(
    private val onMovieClicked: (id: Int) -> Unit
) : ListAdapter<MovieUI, MoviesAdapter.ViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentMovie = getItem(position)

        with(holder) {
            setData(currentMovie)
            itemView.setOnClickListener {
                onMovieClicked(currentMovie.id)
            }
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemMovieBinding.bind(view)

        fun setData(movie: MovieUI) {
            binding.apply {
                itemMovieTitle.text = movie.title.normalize()
                itemMovieImg.setAsyncImage(itemView.context, movie.posterPath)
            }
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<MovieUI>() {
            override fun areItemsTheSame(
                oldItem: MovieUI,
                newItem: MovieUI
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: MovieUI,
                newItem: MovieUI
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}

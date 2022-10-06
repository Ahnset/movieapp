package com.example.movieapp.detail.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.detail.R
import com.example.movieapp.detail.databinding.ItemSimilarMovieBinding
import com.example.movieapp.presentation.common.GlideHelper.setAsyncImage
import com.example.movieapp.presentation.model.MovieUI

class SimilarMovieAdapter(
    private val onMovieClicked: (id: Int) -> Unit
) : ListAdapter<MovieUI, SimilarMovieAdapter.ViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_similar_movie, parent, false)

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
        private val binding = ItemSimilarMovieBinding.bind(view)

        fun setData(movie: MovieUI) {
            binding.apply {
                itemSimilarTitle.text = movie.title
                itemSimilarPoster.setAsyncImage(itemView.context, movie.posterPath)
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

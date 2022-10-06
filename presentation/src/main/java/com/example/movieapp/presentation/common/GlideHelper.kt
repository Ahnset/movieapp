package com.example.movieapp.presentation.common

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.movieapp.presentation.R

object GlideHelper {
    private const val IMAGE_PREFIX = "https://image.tmdb.org/t/p/original/"

    fun ImageView.setAsyncImage(context: Context, path: String?) {
        val imageUrl = "$IMAGE_PREFIX$path"

        Glide.with(context)
            .load(imageUrl)
            .placeholder(R.drawable.movie_placeholder)
            .into(this)
    }

    fun ImageView.setAsyncImageCircled(context: Context, path: String?) {
        val imageUrl = "$IMAGE_PREFIX$path"
        Glide.with(context)
            .load(imageUrl)
            .placeholder(R.drawable.profile_placeholder)
            .circleCrop()
            .into(this)
    }
}

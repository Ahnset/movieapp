package com.example.movieapp.presentation.common

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

object GlideHelper {
    const val IMAGE_PREFIX = "https://image.tmdb.org/t/p/original/"

    fun ImageView.setAsyncImage(context: Context, path: String) {
        val imageUrl = "$IMAGE_PREFIX$path"

        Glide.with(context)
            .load(imageUrl)
            .into(this)
    }
}

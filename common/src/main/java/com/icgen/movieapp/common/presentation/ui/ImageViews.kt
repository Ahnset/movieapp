package com.icgen.movieapp.common.presentation.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.icgen.movieapp.common.R

@Composable
fun ImageCenterCrop(
    path: String?,
    description: String = "",
    placeHolder: Int = R.drawable.movie_placeholder
) {
    AsyncImage(
        model = path,
        contentDescription = description,
        placeholder = painterResource(id = placeHolder),
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
    )
}
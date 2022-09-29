package com.example.movieapp.presentation.mapper

import com.example.movieapp.presentation.model.MovieUI
import com.icgen.movieapp.core.model.Movie

fun Movie.toPresentationModel() =
    MovieUI(id, title, posterPath)

fun MovieUI.toCoreModel() =
    Movie(id, title, posterPath)

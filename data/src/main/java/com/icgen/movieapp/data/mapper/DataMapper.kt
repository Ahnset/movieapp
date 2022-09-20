package com.icgen.movieapp.data.mapper

import com.icgen.movieapp.core.model.Movie
import com.icgen.movieapp.data.model.MovieData

fun MovieData.toCoreModel() =
    Movie(id, title, posterPath)
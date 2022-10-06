package com.example.movieapp.presentation.mapper

import com.example.movieapp.core.model.Cast
import com.example.movieapp.core.model.Detail
import com.example.movieapp.core.model.Genre
import com.example.movieapp.core.model.Video
import com.example.movieapp.presentation.model.CastUI
import com.example.movieapp.presentation.model.DetailUI
import com.example.movieapp.presentation.model.GenreUI
import com.example.movieapp.presentation.model.MovieUI
import com.example.movieapp.presentation.model.VideoUI
import com.icgen.movieapp.core.model.Movie

fun Movie.toPresentationModel() =
    MovieUI(id, title, posterPath)

fun MovieUI.toCoreModel() =
    Movie(id, title, posterPath)

fun Genre.toPresentationModel() =
    GenreUI(id, name)

fun Video.toPresentationModel() =
    VideoUI(id, type, key)

fun Cast.toPresentationModel() =
    CastUI(id, name, profilePath)

fun Detail.toPresentationModel() =
    DetailUI(
        id,
        title,
        overview,
        genres.map { it.toPresentationModel() },
        posterPath,
        popularity,
        voteAverage,
        voteCount,
        status,
        runtime,
        releaseDate
    )

package com.icgen.movieapp.data.model

data class DetailData(
    val id: Int,
    val title: String,
    val overview: String,
    val genres: List<GenreData>,
    val posterPath: String?,
    val popularity: Double,
    val voteAverage: Double,
    val voteCount: Int,
    val status: String,
    val runtime: Int,
    val releaseDate: String,
)

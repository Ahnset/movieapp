package com.example.movieapp.core.model

data class Detail(
    val id: Int,
    val title: String,
    val overview: String,
    val genres: List<Genre>,
    val posterPath: String?,
    val popularity: Double,
    val voteAverage: Double,
    val voteCount: Int,
    val status: String,
    val runtime: Int,
    val releaseDate: String,
)

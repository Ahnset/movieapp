package com.example.movieapp.presentation.model

data class DetailUI(
    val id: Int,
    val title: String,
    val overview: String,
    val genres: List<GenreUI>,
    val posterPath: String?,
    val popularity: Double,
    val voteAverage: Double,
    val voteCount: Int,
    val status: String,
    val runtime: Int,
    val releaseDate: String,
)

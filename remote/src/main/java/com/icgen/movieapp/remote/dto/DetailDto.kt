package com.icgen.movieapp.remote.dto

import com.google.gson.annotations.SerializedName

data class DetailDto(
    val id: Int,
    val title: String,
    val overview: String,
    val genres: List<GenreDto>,
    @SerializedName("poster_path") val posterPath: String?,
    val popularity: Double,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int,
    val status: String,
    val runtime: Int,
    @SerializedName("release_date") val releaseDate: String,
)

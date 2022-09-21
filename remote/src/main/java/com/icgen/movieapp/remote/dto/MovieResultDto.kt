package com.icgen.movieapp.remote.dto

import com.google.gson.annotations.SerializedName

data class MovieResultDto(
    val id: Int,
    val title: String,
    @SerializedName("poster_path") val posterPath: String
)

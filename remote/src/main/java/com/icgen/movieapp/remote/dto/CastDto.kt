package com.icgen.movieapp.remote.dto

import com.google.gson.annotations.SerializedName

data class CastDto(
    val id: Int,
    val name: String,
    @SerializedName("profile_path") val profilePath: String?
)

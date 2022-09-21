package com.icgen.movieapp.remote.mapper

import com.icgen.movieapp.data.model.MovieData
import com.icgen.movieapp.remote.dto.MovieResultDto

fun MovieResultDto.toDataModel() =
    MovieData(id, title, posterPath)

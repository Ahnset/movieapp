package com.example.movieapp.detail.common

import com.example.movieapp.core.model.Cast
import com.example.movieapp.core.model.Detail
import com.example.movieapp.core.model.Genre
import com.example.movieapp.core.model.Video
import com.example.movieapp.core.usecase.detail.GetMovieInfoUseCase.Output
import com.example.movieapp.detail.redux.DetailAction.GetMovieInfoLoaded
import com.example.movieapp.presentation.mapper.toPresentationModel
import com.icgen.movieapp.core.model.Movie

object TestHelper {

    fun makeMovieInfoOutput() =
        Output(
            Detail(
                0,
                "title",
                "overview",
                listOf(Genre(0, "name")),
                "posterPath",
                0.0,
                0.0,
                0,
                "status",
                0,
                "releaseDate"
            ),
            listOf(Cast(0, "name", "profilePath")),
            listOf(Video("0", "type", "key")),
            listOf(Movie(0, "title", "posterPath"))
        )

    fun makeMovieInfoLoadedFromOutput(output: Output): GetMovieInfoLoaded {
        val (detail, cast, videos, similarMovies) = output

        return GetMovieInfoLoaded(
            detail.toPresentationModel(),
            cast.map { it.toPresentationModel() },
            videos.map { it.toPresentationModel() },
            similarMovies.map { it.toPresentationModel() }
        )
    }

    fun makeMovieInfoLoadedAction(): GetMovieInfoLoaded {
        val output = makeMovieInfoOutput()
        return makeMovieInfoLoadedFromOutput(output)
    }
}

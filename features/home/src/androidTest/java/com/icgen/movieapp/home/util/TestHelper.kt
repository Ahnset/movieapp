package com.icgen.movieapp.home.util

import com.icgen.movieapp.common.domain.model.Detail
import com.icgen.movieapp.common.domain.model.Genre
import com.icgen.movieapp.common.domain.model.Movie
import com.icgen.movieapp.home.domain.usecase.GetCatalogUseCase
import com.icgen.movieapp.home.domain.usecase.GetDetailUseCase
import com.icgen.movieapp.home.presentation.catalog.CatalogState

fun getDummyCatalog(): CatalogState.CatalogLoaded {
    val dummy = GetCatalogUseCase.Output(
        popularMovies = listOf(
            Movie(
                id = 950396,
                title = "The Gorge",
                posterPath = "https://image.tmdb.org/t/p/original/7iMBZzVZtG0oBug4TfqDb9ZxAOa.jpg"
            )
        ),
        trendingMovies = listOf(
            Movie(
                id = 1064213,
                title = "Anora",
                posterPath = "https://image.tmdb.org/t/p/original/cgXk2tNYhJZLXdBDO5DidAVzQ82.jpg"
            )
        ),
        upcomingMovies = listOf(
            Movie(
                id = 278,
                title = "The Shawshank Redemption",
                posterPath = "https://image.tmdb.org/t/p/original/9cqNxx0GxF0bflZmeSMuL5tnGzr.jpg"
            )
        ),
        topRatedMovies = listOf(
            Movie(
                id = 1126166,
                title = "Flight Risk",
                posterPath = "https://image.tmdb.org/t/p/original/4cR3hImKd78dSs652PAkSAyJ5Cx.jpg"
            )
        )
    )

    return CatalogState.CatalogLoaded(dummy)
}

fun getDummyDetail(title: String) =
    GetDetailUseCase.Output(
        detail = makeDetail(title),
        cast = emptyList(),
        videos = emptyList(),
        similarMovies = emptyList()
    )

fun makeDetail(title: String) =
    Detail(
        id = 1,
        title = title,
        overview = "test",
        genres = makeGenres(),
        posterPath = "test",
        popularity = 5.0,
        voteAverage = 7.0,
        voteCount = 3,
        status = "test",
        runtime = 100,
        releaseDate = "test",
    )

fun makeGenres() = listOf(Genre(1, "Action"), Genre(2, "Anime"))
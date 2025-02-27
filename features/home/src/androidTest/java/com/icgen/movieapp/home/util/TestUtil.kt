package com.icgen.movieapp.home.util

import com.icgen.movieapp.common.domain.model.Movie
import com.icgen.movieapp.home.domain.usecase.GetCatalogUseCase
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
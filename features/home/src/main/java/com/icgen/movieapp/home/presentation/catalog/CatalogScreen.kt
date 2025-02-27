package com.icgen.movieapp.home.presentation.catalog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.icgen.movieapp.common.presentation.ui.CatalogErrorScreen
import com.icgen.movieapp.common.presentation.ui.CircularProgress
import com.icgen.movieapp.common.presentation.ui.MovieCarousel
import com.icgen.movieapp.common.presentation.ui.MovieSlider
import com.icgen.movieapp.common.util.ListHelper.sliceOrGet
import com.icgen.movieapp.home.presentation.catalog.CatalogState.CatalogError
import com.icgen.movieapp.home.presentation.catalog.CatalogState.CatalogLoaded
import com.icgen.movieapp.home.presentation.catalog.CatalogState.Loading

@Composable
fun CatalogScreen(
    state: CatalogState,
    onMovieClick: (id: Int) -> Unit = {},
    onRetryClick: () -> Unit
) {
    when (state) {
        is Loading -> CircularProgress()
        is CatalogError -> CatalogErrorScreen(onRetryClick)
        is CatalogLoaded -> ShowCatalogs(state, onMovieClick)
    }
}

@Composable
fun ShowCatalogs(
    state: CatalogLoaded,
    onMovieClick: (id: Int) -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        MovieCarousel(
            items = state.catalog.trendingMovies.sliceOrGet(5),
            onMovieClick = onMovieClick
        )

        MovieSlider(
            title = "Top Rated",
            items = state.catalog.topRatedMovies.sliceOrGet(10),
            onMovieClick = onMovieClick
        )

        MovieSlider(
            title = "Most Popular",
            items = state.catalog.popularMovies.sliceOrGet(10),
            onMovieClick = onMovieClick
        )

        MovieSlider(
            title = "Coming Soon",
            items = state.catalog.upcomingMovies.sliceOrGet(10),
            onMovieClick = onMovieClick
        )
    }
}
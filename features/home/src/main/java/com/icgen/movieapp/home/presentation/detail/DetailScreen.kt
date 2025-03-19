package com.icgen.movieapp.home.presentation.detail

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.icgen.movieapp.common.R
import com.icgen.movieapp.common.domain.model.Cast
import com.icgen.movieapp.common.domain.model.Detail
import com.icgen.movieapp.common.domain.model.Movie
import com.icgen.movieapp.common.domain.model.Video
import com.icgen.movieapp.common.presentation.theme.mainRed
import com.icgen.movieapp.common.presentation.ui.CastSlider
import com.icgen.movieapp.common.presentation.ui.CatalogErrorScreen
import com.icgen.movieapp.common.presentation.ui.CircularProgress
import com.icgen.movieapp.common.presentation.ui.MovieSlider
import com.icgen.movieapp.common.presentation.ui.RatingBar
import com.icgen.movieapp.common.presentation.ui.VerticalFadingRectangle
import com.icgen.movieapp.common.util.ListHelper.stringify
import com.icgen.movieapp.common.util.getScreenHeight
import com.icgen.movieapp.common.util.getScreenWidth
import com.icgen.movieapp.home.presentation.detail.DetailViewState.DetailError
import com.icgen.movieapp.home.presentation.detail.DetailViewState.DetailLoaded
import com.icgen.movieapp.home.presentation.detail.DetailViewState.Loading

@Composable
fun DetailScreen(
    state: DetailViewState,
    onMovieClick: (id: Int) -> Unit = {},
    onBackButtonClick: () -> Unit = {},
    onPlayButtonClick: (context: Context, key: String) -> Unit = {_,_ -> Unit},
    onRetryButtonClick: () -> Unit = {},
) {
    when (state) {
        is DetailLoaded -> DisplayDetail(state, onMovieClick, onBackButtonClick, onPlayButtonClick)
        is Loading -> CircularProgress()
        is DetailError -> { CatalogErrorScreen(onRetryButtonClick) }
    }
}

@Composable
fun DisplayDetail(
    state: DetailLoaded,
    onMovieClick: (id: Int) -> Unit,
    onBackButtonClick: () -> Unit,
    onPlayButtonClick: (context: Context, key: String) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        TrailerBox(
            detail = state.result.detail,
            videos = state.result.videos,
            onBackButtonClick = onBackButtonClick,
            onPlayButtonClick = onPlayButtonClick,
        )

        MovieOverView(
            title = "overview",
            overview = state.result.detail.overview
        )

        MovieCast(
            title = "Cast",
            cast = state.result.cast
        )

        SimilarMovies(
            title = "Similar Movies",
            movies = state.result.similarMovies,
            onMovieClick = onMovieClick
        )
    }
}

@Composable
fun TrailerBox(
    detail: Detail,
    videos: List<Video>,
    onBackButtonClick: () -> Unit = {},
    onPlayButtonClick: (context: Context, key: String) -> Unit
) {
    val context = LocalContext.current
    val mediaHeight = (getScreenHeight() / 2) + 50.dp
    val trailerHeight = mediaHeight - 100.dp
    val coverHeight = 200.dp
    val coverTextWidth = (getScreenWidth() * 2 / 3) - 22.dp

    val key = if (videos.isNotEmpty()) {
        videos[0].key
    } else ""

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(mediaHeight)
    ) {
        val (trailerBox, coverBox) = createRefs()

        // Video Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(trailerHeight)
                .constrainAs(trailerBox) {
                    top.linkTo(parent.top, margin = 0.dp)
                    start.linkTo(parent.start, margin = 0.dp)
                }
        ) {
            // Cover Image Box
            ConstraintLayout(
                modifier = Modifier.fillMaxSize()
            ) {
                val (image, shadow, backButton, playButton) = createRefs()

                // Preview
                AsyncImage(
                    model = detail.posterPath,
                    contentDescription = "Movie Title",
                    placeholder = painterResource(id = R.drawable.movie_placeholder),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .constrainAs(image) {
                            top.linkTo(parent.top, margin = 0.dp)
                            bottom.linkTo(parent.bottom, margin = 0.dp)
                            start.linkTo(parent.start, margin = 0.dp)
                            end.linkTo(parent.end, margin = 0.dp)
                        }
                )

                // Shadow
                Box(
                    modifier = Modifier.constrainAs(shadow) {
                        bottom.linkTo(parent.bottom, margin = 0.dp)
                        start.linkTo(parent.start, margin = 0.dp)
                        end.linkTo(parent.end, margin = 0.dp)
                    }
                ) {
                    VerticalFadingRectangle()
                }

                // Play Button
                FloatingActionButton(
                    onClick = { onPlayButtonClick(context, key) },
                    shape = CircleShape,
                    containerColor = mainRed,
                    contentColor = Color.White,
                    modifier = Modifier
                        .padding(16.dp)
                        .size(72.dp)
                        .constrainAs(playButton) {
                            top.linkTo(parent.top, margin = 0.dp)
                            bottom.linkTo(parent.bottom, margin = 0.dp)
                            start.linkTo(parent.start, margin = 0.dp)
                            end.linkTo(parent.end, margin = 0.dp)
                        }
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Play Button",
                        modifier = Modifier.size(40.dp)
                    )
                }


                // Back Button
                FloatingActionButton(
                    onClick = { onBackButtonClick() },
                    shape = CircleShape,
                    containerColor = Color.DarkGray,
                    contentColor = Color.White,
                    modifier = Modifier
                        .padding(horizontal = 10.dp, vertical = 16.dp)
                        .constrainAs(backButton) {
                            top.linkTo(parent.top, margin = 0.dp)
                            start.linkTo(parent.start, margin = 0.dp)
                        }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Play Button",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }

        // Cover Box
        Box(
            modifier = Modifier
                .height(coverHeight)
                .fillMaxWidth()
                .constrainAs(coverBox) {
                    bottom.linkTo(parent.bottom, margin = 0.dp)
                    start.linkTo(parent.start, margin = 0.dp)
                    end.linkTo(parent.end, margin = 0.dp)
                }
        ) {
            ConstraintLayout(
                modifier = Modifier.fillMaxSize()
            ) {
                val (cover, title, genres, release, rating) = createRefs()

                Card(
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(width = 1.dp, color = Color.DarkGray),
                    modifier = Modifier
                        .padding(horizontal = 5.dp)
                        .fillMaxHeight()
                        .width(130.dp)
                        .constrainAs(cover) {
                            top.linkTo(parent.top, margin = 0.dp)
                            start.linkTo(parent.start, margin = 4.dp)
                        }
                ) {
                    AsyncImage(
                        model = detail.posterPath,
                        contentDescription = "",
                        placeholder = painterResource(id = R.drawable.movie_placeholder),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                // Title
                Text(
                    text = detail.title,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    color = Color.White,
                    modifier = Modifier
                        .width(coverTextWidth)
                        .constrainAs(title) {
                            top.linkTo(cover.top, margin = 16.dp)
                            start.linkTo(cover.end, margin = 4.dp)
                        }
                )

                // Genres
                Text(
                    text = detail.genres.stringify(),
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    color = Color.White,
                    modifier = Modifier
                        .width(coverTextWidth)
                        .constrainAs(genres) {
                            top.linkTo(title.bottom, margin = 4.dp)
                            start.linkTo(title.start, margin = 0.dp)
                        }
                )

                // Release Date
                Text(
                    text = detail.releaseDate,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    color = Color.White,
                    modifier = Modifier
                        .width(coverTextWidth)
                        .constrainAs(release) {
                            top.linkTo(genres.bottom, margin = 4.dp)
                            start.linkTo(genres.start, margin = 0.dp)
                        }
                )

                // Rate
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .constrainAs(rating) {
                            top.linkTo(release.bottom, margin = 4.dp)
                            start.linkTo(release.start, margin = 0.dp)
                        }
                ) {
                    RatingBar(rating = detail.voteAverage / 2)
                    Text(
                        text = "(${detail.voteCount})",
                        color = Color.White,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }

        }
    }
}

// Overview
@Composable
fun MovieOverView(title: String, overview: String) {
    Spacer(modifier = Modifier.padding(vertical = 16.dp))

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Text(
            text = title,
            color = Color.White,
            style = MaterialTheme.typography.titleLarge
        )

        Text(
            text = overview,
            color = Color.White,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

// Cast
@Composable
fun MovieCast(
    title: String,
    cast: List<Cast>
) {
    if (cast.isNotEmpty()) {
        Spacer(modifier = Modifier.padding(vertical = 16.dp))
        CastSlider(title, cast)
    }
}

// Similar Movies
@Composable
fun SimilarMovies(
    title: String,
    movies: List<Movie>,
    onMovieClick: (id: Int) -> Unit
) {
    if (movies.isNotEmpty()) {
        Spacer(modifier = Modifier.padding(vertical = 16.dp))
        MovieSlider(title, movies, onMovieClick)
    }
}
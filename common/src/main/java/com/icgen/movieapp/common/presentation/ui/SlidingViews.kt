package com.icgen.movieapp.common.presentation.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.icgen.movieapp.common.R
import com.icgen.movieapp.common.domain.model.Cast
import com.icgen.movieapp.common.domain.model.Movie
import com.icgen.movieapp.common.presentation.theme.mainRed
import com.icgen.movieapp.common.util.StringHelper.normalize
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieCarousel(
    items: List<Movie>,
    onMovieClick: (id: Int) -> Unit = {}
) {
    val pagerState = rememberPagerState(pageCount = { items.size })
    val pagerIsDragged by pagerState.interactionSource.collectIsDraggedAsState()
    val pageInteractionSource = remember { MutableInteractionSource() }
    val pageIsPressed by pageInteractionSource.collectIsPressedAsState()
    val autoAdvance = !pagerIsDragged && !pageIsPressed

    if (autoAdvance) {
        LaunchedEffect(pagerState, pageInteractionSource) {
            while (true) {
                delay(2000)
                val nextPage = (pagerState.currentPage + 1) % items.size
                pagerState.animateScrollToPage(nextPage)
            }
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        HorizontalPager(
            state = pagerState,
        ) { pageIndex ->
            Card(
                shape = RoundedCornerShape(0.dp),
                modifier = Modifier
                    .height(300.dp)
                    .fillMaxWidth()
                    .clickable(
                        interactionSource = pageInteractionSource,
                        indication = null
                    ) {
                        onMovieClick(items[pageIndex].id)
                    }
            ) {
                // Card content
                Box(
                    contentAlignment = Alignment.BottomCenter,
                    modifier = Modifier.fillMaxSize()
                ) {
                    ImageCenterCrop(path = items[pageIndex].posterPath)

                    VerticalFadingRectangle()

                    MovieTitle(title = items[pageIndex].title, size = 22.sp)
                }
            }
        }

        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(bottom = 8.dp),
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color = if (pagerState.currentPage == iteration) mainRed
                else Color.LightGray

                Box(
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 16.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(8.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieSlider(
    title: String,
    items: List<Movie>,
    onMovieClick: (id: Int) -> Unit = {}
) {
    val pagerState = rememberPagerState(pageCount = { items.size })

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.fillMaxWidth()
    ) {
        SectionTitle(title)

        HorizontalPager(
            state = pagerState,
            pageSize = PageSize.Fixed(150.dp)
        ) { pageIndex ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        onMovieClick(items[pageIndex].id)
                    }
            ) {
                Card(
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(width = 1.dp, color = Color.DarkGray),
                    modifier = Modifier
                        .padding(horizontal = 5.dp)
                        .height(200.dp)
                        .fillMaxWidth()
                ) {
                    ImageCenterCrop(path = items[pageIndex].posterPath)
                }

                MovieTitle(items[pageIndex].title.normalize())
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CastSlider(
    title: String,
    items: List<Cast>
) {
    val pagerState = rememberPagerState(pageCount = { items.size })

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.fillMaxWidth()
    ) {
        SectionTitle(title)

        HorizontalPager(
            state = pagerState,
            pageSize = PageSize.Fixed(120.dp)
        ) { pageIndex ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
            ) {
                Card(
                    shape = CircleShape,
                    border = BorderStroke(width = 1.dp, color = Color.DarkGray),
                    modifier = Modifier
                        .padding(0.dp)
                        .size(90.dp)
                ) {
                    ImageCenterCrop(
                        path = items[pageIndex].profilePath,
                        placeHolder = R.drawable.profile_placeholder
                    )
                }

                Text(
                    text = items[pageIndex].name.normalize(),
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
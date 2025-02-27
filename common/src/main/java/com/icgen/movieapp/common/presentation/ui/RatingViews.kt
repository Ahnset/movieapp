package com.icgen.movieapp.common.presentation.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.StarHalf
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RatingBar(
    rating: Double,
    maxRating: Int = 5,
) {
    val ratingInt = rating.toInt()
    val decimalPoint = (rating % 1)
    var isHalfStart = decimalPoint > 0
    Row {
        for (i in 1..maxRating) {
            if (i <= ratingInt) FilledStar()
            else if (isHalfStart) HalfStar().also { isHalfStart = false }
            else OutlinedStar()
        }
    }
}

@Composable
fun FilledStar() {
    Icon(
        imageVector = Icons.Default.Star,
        contentDescription = "Filled Rating Star",
        tint = Color.Cyan,
        modifier = Modifier.size(20.dp)
    )
}

@Composable
fun HalfStar() {
    Icon(
        imageVector = Icons.AutoMirrored.Filled.StarHalf,
        contentDescription = "Half Rating Star",
        tint = Color.Cyan,
        modifier = Modifier.size(20.dp)
    )
}

@Composable
fun OutlinedStar() {
    Icon(
        imageVector = Icons.Outlined.StarOutline,
        contentDescription = "Outlined Rating Star",
        tint = Color.Cyan,
        modifier = Modifier.size(20.dp)
    )
}
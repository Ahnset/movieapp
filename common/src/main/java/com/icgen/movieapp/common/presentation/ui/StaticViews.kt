package com.icgen.movieapp.common.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.icgen.movieapp.common.R
import com.icgen.movieapp.common.presentation.theme.mainRed
import com.icgen.movieapp.common.util.getScreenWidth

@Composable
fun VerticalFadingRectangle(
    colors: List<Color> = listOf(Color.Transparent, MaterialTheme.colorScheme.background),
    startY: Float = 0.0f,
    endY: Float = 250.0f,
    height: Dp = 100.dp
) {
    val verticalBrush = Brush.verticalGradient(colors, startY, endY)
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(height)
        .background(verticalBrush))
}

@Composable
fun CatalogErrorScreen(
    onRetryClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Network Error",
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Image(
            painter = painterResource(R.drawable.cloud_data),
            contentDescription = "Error Icon",
            contentScale = ContentScale.Crop,
            modifier = Modifier.width(getScreenWidth() - 64.dp)
        )

        Text(
            text = "The server is not reachable at the moment.",
            fontWeight = FontWeight.Normal,
            fontSize = 22.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Button(
            onClick = { onRetryClick() },
            colors = ButtonDefaults.buttonColors(containerColor = mainRed),
            shape = RoundedCornerShape(8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = "Retry Icon",
                tint = Color.White
            )
            Text(
                text = "Retry",
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                modifier = Modifier.padding(start = 4.dp)
            )
        }
    }
}
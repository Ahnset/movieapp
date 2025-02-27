package com.icgen.movieapp.common.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.icgen.movieapp.common.presentation.theme.mainRed

@Composable
fun CircularProgress() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().semantics {
            this.contentDescription = "CircularProgress"
        }
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = Color.DarkGray,
            trackColor = mainRed,
        )
    }
}
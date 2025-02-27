package com.icgen.movieapp.common.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@Composable
fun getScreenWidth() = LocalConfiguration.current.screenWidthDp.dp

@Composable
fun getScreenHeight() = LocalConfiguration.current.screenHeightDp.dp
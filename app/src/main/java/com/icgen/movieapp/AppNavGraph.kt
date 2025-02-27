package com.icgen.movieapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.icgen.movieapp.home.homeNavGraph
import com.icgen.movieapp.common.presentation.navigation.HomeRoute.HOME_GRAPH

@Composable
fun AppNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = HOME_GRAPH // Set start destination here
    ) {
        homeNavGraph(navController = navController)
    }
}

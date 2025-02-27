package com.icgen.movieapp.home

import android.content.Context
import android.content.Intent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.icgen.movieapp.common.presentation.navigation.HomeRoute.HOME_CATALOG_ROUTE
import com.icgen.movieapp.common.presentation.navigation.HomeRoute.HOME_DETAIL_ROUTE
import com.icgen.movieapp.common.presentation.navigation.HomeRoute.HOME_GRAPH
import com.icgen.movieapp.common.presentation.navigation.NavigationConstants.ID_PARAM
import com.icgen.movieapp.common.presentation.navigation.NavigationConstants.KEY_PARAM
import com.icgen.movieapp.home.presentation.catalog.CatalogScreen
import com.icgen.movieapp.home.presentation.catalog.CatalogViewModel
import com.icgen.movieapp.home.presentation.detail.DetailScreen
import com.icgen.movieapp.home.presentation.detail.DetailViewModel
import com.icgen.movieapp.home.presentation.player.PlayerActivity

fun NavGraphBuilder.homeNavGraph(navController: NavController) {
    navigation(
        route = HOME_GRAPH,
        startDestination = HOME_CATALOG_ROUTE
    ) {
        fun navigateToDetail(id: Int) = navController.navigate("$HOME_DETAIL_ROUTE/$id")
        fun navigateUp() = navController.navigateUp()

        fun launchPlayer(context: Context, key: String) {
            val intent = Intent(context, PlayerActivity::class.java)
            intent.putExtra(KEY_PARAM, key)
            context.startActivity(intent)
        }

        composable(route = HOME_CATALOG_ROUTE) {
            val viewModel: CatalogViewModel = hiltViewModel()
            fun onRetryClick() = viewModel.retry()

            CatalogScreen(
                state = viewModel.state.value,
                onMovieClick = ::navigateToDetail,
                onRetryClick = ::onRetryClick
            )
        }

        composable(
            route = "$HOME_DETAIL_ROUTE/{$ID_PARAM}",
            arguments = listOf(
                navArgument(ID_PARAM) { type = NavType.IntType }
            )
        ) {
            val viewModel: DetailViewModel = hiltViewModel()

            DetailScreen(
                state = viewModel.state.value,
                onMovieClick = ::navigateToDetail,
                onBackButtonClick = ::navigateUp,
                onPlayButtonClick = ::launchPlayer
            )
        }
    }
}
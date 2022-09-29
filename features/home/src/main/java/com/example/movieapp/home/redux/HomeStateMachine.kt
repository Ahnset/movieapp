package com.example.movieapp.home.redux

import com.example.movieapp.core.redux.Reducer
import com.example.movieapp.core.redux.StateMachine
import com.example.movieapp.home.redux.HomeAction.GetHomeCatalogs
import com.example.movieapp.home.redux.HomeAction.GetHomeCatalogsError
import com.example.movieapp.home.redux.HomeAction.GetHomeCatalogsLoaded
import com.example.movieapp.home.redux.HomeAction.Idle
import javax.inject.Inject

class HomeStateMachine @Inject constructor(
    homeMiddleware: HomeMiddleware
) : StateMachine<HomeState, HomeAction>(homeMiddleware) {

    override val initialState = HomeState.Idle

    override val reducer: Reducer<HomeState, HomeAction> = { currentState, action ->
        when (action) {
            is Idle -> currentState
            is GetHomeCatalogs -> HomeState.GetHomeCatalogsStarted
            is GetHomeCatalogsError -> HomeState.HomeCatalogsError(action.message)
            is GetHomeCatalogsLoaded -> {
                HomeState.HomeCatalogsLoaded(
                    popularMovies = action.popularMovies,
                    trendingMovies = action.trendingMovies,
                    topRatedMovies = action.topRatedMovies,
                    upcomingMovies = action.upcomingMovies,
                )
            }
        }
    }
}

package com.example.movieapp.home.redux

import androidx.lifecycle.viewModelScope
import com.example.movieapp.presentation.base.ReduxViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    stateMachine: HomeStateMachine,
    private val client: OkHttpClient
) : ReduxViewModel<HomeState, HomeAction>(stateMachine) {

    fun start() {
        viewModelScope.launch {
            process(HomeAction.GetHomeCatalogs)
        }
    }

    fun retry() {
        client.cache?.evictAll()
        start()
    }
}

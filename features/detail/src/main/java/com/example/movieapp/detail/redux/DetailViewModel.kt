package com.example.movieapp.detail.redux

import androidx.lifecycle.viewModelScope
import com.example.movieapp.presentation.base.ReduxViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    stateMachine: DetailStateMachine
) : ReduxViewModel<DetailState, DetailAction>(stateMachine) {

    fun start(movieId: Int) {
        viewModelScope.launch {
            process(DetailAction.GetMovieInfo(movieId))
        }
    }
}

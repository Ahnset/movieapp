package com.example.movieapp.presentation.base

import androidx.lifecycle.ViewModel
import com.example.movieapp.core.redux.Action
import com.example.movieapp.core.redux.State
import com.example.movieapp.core.redux.StateMachine
import kotlinx.coroutines.flow.StateFlow

abstract class ReduxViewModel<S : State, A : Action>(
    private val stateMachine: StateMachine<S, A>
) : ViewModel() {

    init { stateMachine.build() }

    val viewState: StateFlow<S>? = stateMachine.getViewState()
    protected suspend fun process(action: A) = stateMachine.dispatch(action)
}

package com.example.movieapp.core.redux

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class Store<S : State, A : Action> {

    abstract val reducer: Reducer<S, A>
    abstract val initialState: S
    abstract suspend fun dispatch(action: A)

    protected var _state: MutableStateFlow<S>? = null
        private set

    var state: StateFlow<S>? = null
        private set

    fun build() {
        if (_state != null) return
        _state = MutableStateFlow(initialState)
        state = _state
    }
}

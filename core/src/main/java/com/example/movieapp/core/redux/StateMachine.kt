package com.example.movieapp.core.redux

abstract class StateMachine<S : State, A : Action>(
    private val middleware: Middleware<S, A>? = null
) : Store<S, A>() {

    fun getViewState() = state

    override suspend fun dispatch(action: A) {
        val currentState = _state?.value
        currentState?.let {
            val newState = reducer.invoke(currentState, action)
            _state?.value = newState
            middleware?.dispatch(newState, action, this)
        }
    }
}

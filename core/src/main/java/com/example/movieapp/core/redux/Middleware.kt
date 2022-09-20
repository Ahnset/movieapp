package com.example.movieapp.core.redux

interface Middleware<S : State, A : Action> {
    suspend fun dispatch(currentState: S, action: A, store: Store<S, A>)
}

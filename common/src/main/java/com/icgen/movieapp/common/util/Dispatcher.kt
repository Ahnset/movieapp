package com.icgen.movieapp.common.util

import kotlinx.coroutines.CoroutineDispatcher

data class Dispatcher(
    val io: CoroutineDispatcher,
    val main: CoroutineDispatcher,
    val default: CoroutineDispatcher,
)

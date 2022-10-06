package com.example.movieapp.presentation.common

object StringHelper {
    private const val ELLIPSIS = "..."
    private const val NORMALIZED_LIMIT = 14

    fun String.normalize(): String =
        when {
            (this.length < NORMALIZED_LIMIT) -> this
            else -> "${this.take(NORMALIZED_LIMIT)}$ELLIPSIS"
        }
}

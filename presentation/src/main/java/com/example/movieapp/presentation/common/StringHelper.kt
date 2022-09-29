package com.example.movieapp.presentation.common

object StringHelper {
    private const val ELLIPSIS = "..."
    private const val NORMALIZED_LIMIT = 22

    fun String.normalize(): String =
        when {
            this.length < NORMALIZED_LIMIT -> this
            else -> "${this.take(14)}$ELLIPSIS"
        }
}

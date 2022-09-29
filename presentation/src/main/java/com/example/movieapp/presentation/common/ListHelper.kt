package com.example.movieapp.presentation.common

object ListHelper {
    fun <T> List<T>.sliceOrGet(index: Int): List<T> =
        when {
            index < 0 -> this
            size > index -> this.slice(0..index)
            else -> this
        }
}

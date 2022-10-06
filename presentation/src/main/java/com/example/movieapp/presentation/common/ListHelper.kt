package com.example.movieapp.presentation.common

import com.example.movieapp.presentation.model.GenreUI

object ListHelper {
    private const val GENRE_LIMIT = 3
    private const val GENRE_SEPARATOR = " | "

    fun <T> List<T>.sliceOrGet(index: Int): List<T> =
        when {
            (size > index) -> this.slice(0..index)
            else -> this
        }

    fun List<GenreUI>.stringify(): String {
        val stringBuilder = StringBuilder()

        when {
            (size < GENRE_LIMIT) -> {
                this.forEachIndexed { index, _ ->
                    appendGenre(stringBuilder, index)
                }
            }
            else -> {
                repeat(GENRE_LIMIT) { index ->
                    appendGenre(stringBuilder, index)
                }
            }
        }

        return stringBuilder.toString()
    }

    private fun List<GenreUI>.appendGenre(
        string: StringBuilder,
        index: Int
    ) {
        string.append(get(index).name)
        if (index < size - 1) string.append(GENRE_SEPARATOR)
    }
}

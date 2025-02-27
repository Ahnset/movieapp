package com.icgen.movieapp.common.util

import com.icgen.movieapp.common.domain.model.Genre

object ListHelper {
    private const val GENRE_LIMIT = 3
    private const val GENRE_SEPARATOR = " | "

    fun <T> List<T>.sliceOrGet(index: Int): List<T> =
        when {
            (size > index) -> this.slice(0..index)
            else -> this
        }

    fun List<Genre>.stringify(): String {
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

    private fun List<Genre>.appendGenre(
        string: StringBuilder,
        index: Int
    ) {
        string.append(get(index).name)

        when (size < GENRE_LIMIT) {
            true -> if (index < size - 1) string.append(GENRE_SEPARATOR)
            else -> if (index < GENRE_LIMIT - 1) string.append(GENRE_SEPARATOR)
        }
    }
}
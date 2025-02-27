package com.icgen.movieapp.common.util

import android.app.Application
import androidx.annotation.StringRes
import com.icgen.movieapp.common.R
import javax.inject.Inject

object StringHelper {
    private const val ELLIPSIS = "..."
    private const val NORMALIZED_LIMIT = 13

    fun String.normalize(): String =
        when {
            (this.length < NORMALIZED_LIMIT) -> this
            else -> "${this.take(NORMALIZED_LIMIT)}$ELLIPSIS"
        }

    fun getGenericErrorMessage(provider: StringProvider) =
        provider.getString(R.string.server_not_available)
}

class StringProvider @Inject constructor(
    private val application: Application
) {
    fun getString(@StringRes id: Int): String = application.getString(id)
}

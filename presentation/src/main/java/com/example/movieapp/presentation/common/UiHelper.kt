package com.example.movieapp.presentation.common

import android.view.View
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar

object UiHelper {
    fun showSnackMessage(message: String, view: View) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
            .show()
    }

    fun hideViews(vararg view: View) {
        view.forEach {
            it.isVisible = false
        }
    }

    fun showViews(vararg view: View) {
        view.forEach {
            it.isVisible = true
        }
    }
}

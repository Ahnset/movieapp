package com.icgen.movieapp.common.util

import android.view.View
import androidx.core.view.isVisible

fun hideViews(vararg view: View) {
    view.forEach { it.isVisible = false }
}

fun showViews(vararg view: View) {
    view.forEach { it.isVisible = true }
}
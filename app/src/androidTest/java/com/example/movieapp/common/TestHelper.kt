package com.example.movieapp.common

import android.view.View
import android.widget.HorizontalScrollView
import android.widget.ListView
import android.widget.ScrollView
import androidx.core.widget.NestedScrollView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.Visibility
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import org.hamcrest.Matcher
import org.hamcrest.Matchers

object TestHelper {

    class NestedScrollTo(
        scrollToAction: ViewAction = ViewActions.scrollTo()
    ) : ViewAction by scrollToAction {
        override fun getConstraints(): Matcher<View> {
            return Matchers.allOf(
                withEffectiveVisibility(Visibility.VISIBLE),
                isDescendantOfA(
                    Matchers.anyOf(
                        isAssignableFrom(
                            NestedScrollView::class.java
                        ),
                        isAssignableFrom(ScrollView::class.java),
                        isAssignableFrom(HorizontalScrollView::class.java),
                        isAssignableFrom(ListView::class.java)
                    )
                )
            )
        }
    }
}

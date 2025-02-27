package com.icgen.movieapp.home.presentation.player

import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.ComponentActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.icgen.movieapp.common.presentation.navigation.NavigationConstants.KEY_PARAM
import com.icgen.movieapp.common.util.hideViews
import com.icgen.movieapp.common.util.showViews
import com.icgen.movieapp.home.R
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class PlayerActivity : ComponentActivity() {

    private lateinit var rootView: LinearLayout
    private lateinit var youTubePlayerView: YouTubePlayerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        rootView = findViewById(R.id.rootView)

        setupFullScreenMode()
        setupPlayer()
    }

    private fun setupFullScreenMode() {
        val windowInsetsController =
            WindowCompat.getInsetsController(window, window.decorView)

        windowInsetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

        ViewCompat.setOnApplyWindowInsetsListener(window.decorView) { view, windowInsets ->
            if (windowInsets.isVisible(WindowInsetsCompat.Type.navigationBars())
                || windowInsets.isVisible(WindowInsetsCompat.Type.statusBars())
            ) {
                windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
            }

            ViewCompat.onApplyWindowInsets(view, windowInsets)
        }
    }

    private fun setupPlayer() {
        val key = intent.getStringExtra(KEY_PARAM) ?: KEY_PARAM
        youTubePlayerView = YouTubePlayerView(this)

        hideViews(youTubePlayerView)
        lifecycle.addObserver(youTubePlayerView)

        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                showViews(youTubePlayerView)
                youTubePlayerView.enterFullScreen()
                youTubePlayer.loadVideo(key, 0F)
            }
        })

        rootView.addView(youTubePlayerView)
    }

    override fun onDestroy() {
        super.onDestroy()
        youTubePlayerView.release()
    }
}
package com.example.movieapp.detail.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.movieapp.detail.databinding.ActivityPlayerBinding
import com.example.movieapp.presentation.common.PlayerHelper.VIDEO_KEY
import com.example.movieapp.presentation.common.UiHelper.hideViews
import com.example.movieapp.presentation.common.UiHelper.showViews
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class PlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupPlayer()
    }

    private fun setupPlayer() {
        val key = intent.getStringExtra(VIDEO_KEY) ?: VIDEO_KEY
        val youTubePlayerView = YouTubePlayerView(this)
        hideViews(youTubePlayerView)
        lifecycle.addObserver(youTubePlayerView)
        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                showViews(youTubePlayerView)
                youTubePlayer.loadVideo(key, 0F)
            }
        })
        binding.rootView.addView(youTubePlayerView)
    }
}

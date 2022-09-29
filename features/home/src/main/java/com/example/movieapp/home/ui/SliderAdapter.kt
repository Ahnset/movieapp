package com.example.movieapp.home.ui

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.example.movieapp.home.R
import com.example.movieapp.home.databinding.ItemSlideBinding
import com.example.movieapp.presentation.common.GlideHelper.setAsyncImage
import com.example.movieapp.presentation.common.StringHelper.normalize
import com.example.movieapp.presentation.model.MovieUI

class SliderAdapter(
    private val movies: List<MovieUI>,
    private val onMovieClicked: (id: Int) -> Unit,
) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val context = container.context
        val currentMovie = movies[position]
        val inflater = context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val slideLayout = inflater.inflate(R.layout.item_slide, null)

        bindSliderItem(slideLayout, currentMovie, context)
        container.addView(slideLayout)

        return slideLayout
    }

    private fun bindSliderItem(
        slideLayout: View,
        currentMovie: MovieUI,
        context: Context
    ) {
        val binding = ItemSlideBinding.bind(slideLayout)
        binding.apply {
            setSlideTitle(currentMovie)
            setSlidePoster(context, currentMovie)
            setSlideItemClickListener(currentMovie)
        }
    }

    private fun ItemSlideBinding.setSlideItemClickListener(
        currentMovie: MovieUI
    ) {
        sliderContainer.setOnClickListener {
            onMovieClicked(currentMovie.id)
        }
    }

    private fun ItemSlideBinding.setSlidePoster(
        context: Context,
        currentMovie: MovieUI
    ) {
        slideImage.setAsyncImage(
            context,
            currentMovie.posterPath
        )
    }

    private fun ItemSlideBinding.setSlideTitle(currentMovie: MovieUI) {
        slideTitle.text = currentMovie.title.normalize()
    }

    override fun getCount(): Int {
        return movies.size
    }

    override fun isViewFromObject(view: View, o: Any): Boolean {
        return view === o
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}

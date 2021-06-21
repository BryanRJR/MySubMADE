package com.dicoding.picodiploma.mysubmade.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.dicoding.picodiploma.mysubmade.R
import com.dicoding.picodiploma.mysubmade.core.model.Movie
import com.dicoding.picodiploma.mysubmade.databinding.ActivityDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    companion object {
        const val DATA = "data"
    }

    private lateinit var bindingDetail: ActivityDetailBinding
    private val viewModelDetail: DetailMovieViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingDetail = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(bindingDetail.root)

        bindingDetail.viewToolbar.setNavigationOnClickListener { onBackPressed() }

        val detailMovie = intent.getParcelableExtra<Movie>(DATA)
        showDetailMovie(detailMovie)
    }

    private fun showDetailMovie(detailMovie: Movie?) {
        detailMovie?.let {
            with(bindingDetail) {
                Glide.with(this@DetailActivity)
                    .load("https://image.tmdb.org/t/p/w500" + detailMovie.coverMovie)
                    .transform(RoundedCorners(20))
                    .into(detailImage)

                textTitleDetail.text = detailMovie.title
                textDateDetail.text = detailMovie.date
                textRatingDetail.text = detailMovie.vote_average.toString()
                textDesc.text = detailMovie.description

                var favStatus = detailMovie.isFavorite
                setStatusFav(favStatus)
                fab.setOnClickListener {
                    favStatus = !favStatus
                    viewModelDetail.setMovieFav(detailMovie, favStatus)
                    setStatusFav(favStatus)
                }
            }
        }
    }

    private fun setStatusFav(statusFav: Boolean) {
        if (statusFav) {
            bindingDetail.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_24))
        } else {
            bindingDetail.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_border_24))
        }
    }
}
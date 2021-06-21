package com.dicoding.picodiploma.mysubmade.detail

import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.mysubmade.core.model.Movie
import com.dicoding.picodiploma.mysubmade.core.useCase.MovieUseCase


class DetailMovieViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun setMovieFav(movie: Movie, newState: Boolean) = movieUseCase.setFavMovie(movie, newState)
}
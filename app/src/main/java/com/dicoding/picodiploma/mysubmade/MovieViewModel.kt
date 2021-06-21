package com.dicoding.picodiploma.mysubmade

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.picodiploma.mysubmade.core.useCase.MovieUseCase

class MovieViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val movie = movieUseCase.getListMovie().asLiveData()


}
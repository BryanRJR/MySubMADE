package com.dicoding.picodiploma.mysubmade.di

import com.dicoding.picodiploma.mysubmade.MovieViewModel
import com.dicoding.picodiploma.mysubmade.core.useCase.InteractorMovie
import com.dicoding.picodiploma.mysubmade.core.useCase.MovieUseCase
import com.dicoding.picodiploma.mysubmade.detail.DetailMovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val useCaseModule = module {
    factory<MovieUseCase> { InteractorMovie(get()) }
}

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { DetailMovieViewModel(get()) }
}
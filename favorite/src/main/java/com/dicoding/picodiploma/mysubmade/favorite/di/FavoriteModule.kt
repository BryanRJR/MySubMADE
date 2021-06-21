package com.dicoding.picodiploma.mysubmade.favorite.di


import com.dicoding.picodiploma.mysubmade.favorite.FavoriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavoriteViewModel(get()) }
}
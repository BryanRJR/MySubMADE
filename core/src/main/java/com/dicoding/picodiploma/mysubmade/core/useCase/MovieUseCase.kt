package com.dicoding.picodiploma.mysubmade.core.useCase

import com.dicoding.picodiploma.mysubmade.core.model.Movie
import com.dicoding.picodiploma.mysubmade.core.source.Resource
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getListMovie(): Flow<Resource<List<Movie>>>
    fun getFavMovie(): Flow<List<Movie>>
    fun setFavMovie(movie: Movie, state: Boolean)
}
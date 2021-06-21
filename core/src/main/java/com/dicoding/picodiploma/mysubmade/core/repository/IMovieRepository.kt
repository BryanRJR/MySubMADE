package com.dicoding.picodiploma.mysubmade.core.repository

import com.dicoding.picodiploma.mysubmade.core.model.Movie
import com.dicoding.picodiploma.mysubmade.core.source.Resource
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getListMovie(): Flow<Resource<List<Movie>>>
    fun getFavoriteMovie(): Flow<List<Movie>>
    fun setFavoriteMovie(movie: Movie, state: Boolean)
}
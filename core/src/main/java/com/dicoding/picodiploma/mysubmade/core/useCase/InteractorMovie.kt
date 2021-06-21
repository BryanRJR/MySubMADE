package com.dicoding.picodiploma.mysubmade.core.useCase

import com.dicoding.picodiploma.mysubmade.core.model.Movie
import com.dicoding.picodiploma.mysubmade.core.repository.IMovieRepository
import com.dicoding.picodiploma.mysubmade.core.source.Resource
import kotlinx.coroutines.flow.Flow

class InteractorMovie (private val movieRepository: IMovieRepository) : MovieUseCase {
    override fun getListMovie(): Flow<Resource<List<Movie>>> = movieRepository.getListMovie()
    override fun getFavMovie(): Flow<List<Movie>> = movieRepository.getFavoriteMovie()
    override fun setFavMovie(movie: Movie, state: Boolean) =
        movieRepository.setFavoriteMovie(movie, state)
}
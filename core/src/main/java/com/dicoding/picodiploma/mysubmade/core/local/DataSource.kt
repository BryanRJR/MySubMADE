package com.dicoding.picodiploma.mysubmade.core.local

import com.dicoding.picodiploma.mysubmade.core.local.entitiy.MovieEntity
import com.dicoding.picodiploma.mysubmade.core.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class DataSource(private val movieDao: MovieDao) {
    fun getListMovie(): Flow<List<MovieEntity>> = movieDao.getListMovie()

    fun getFavMovie(): Flow<List<MovieEntity>> = movieDao.getFavMovie()

    suspend fun insertMovie(movieList: List<MovieEntity>) = movieDao.insertMovieToFav(movieList)

    fun setFavMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFav = newState
        movieDao.updateFavMovie(movie)
    }
}
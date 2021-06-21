package com.dicoding.picodiploma.mysubmade.core.source

import com.dicoding.picodiploma.mysubmade.core.local.DataSource
import com.dicoding.picodiploma.mysubmade.core.model.Movie
import com.dicoding.picodiploma.mysubmade.core.remote.RemoteDataSource
import com.dicoding.picodiploma.mysubmade.core.remotenet.ApiResponse
import com.dicoding.picodiploma.mysubmade.core.remoteres.MovieRes
import com.dicoding.picodiploma.mysubmade.core.repository.IMovieRepository
import com.dicoding.picodiploma.mysubmade.core.utils.AppExecutors
import com.dicoding.picodiploma.mysubmade.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository(
    private val remoteData: RemoteDataSource,
    private val dataSource: DataSource,
    private val applicationExecutors: AppExecutors
    ) : IMovieRepository {


    override fun getListMovie(): Flow<Resource<List<Movie>>> =
        object : com.dicoding.picodiploma.mysubmade.core.source.NetworkBoundResource<List<Movie>, List<MovieRes>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return dataSource.getListMovie().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieRes>>> =
                remoteData.getListMovie()

            override suspend fun saveCallResult(data: List<MovieRes>) {
                val movieList = DataMapper.mapResponseToEntities(data)
                dataSource.insertMovie(movieList)
            }
        }.asFlow()

    override fun getFavoriteMovie(): Flow<List<Movie>> {
        return dataSource.getFavMovie().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val moviesEntity = DataMapper.mapDomainToEntity(movie)
        applicationExecutors.diskIO().execute {
            dataSource.setFavMovie(moviesEntity, state)
        }
    }


}
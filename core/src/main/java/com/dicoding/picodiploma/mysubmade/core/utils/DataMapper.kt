package com.dicoding.picodiploma.mysubmade.core.utils

import com.dicoding.picodiploma.mysubmade.core.local.entitiy.MovieEntity
import com.dicoding.picodiploma.mysubmade.core.model.Movie
import com.dicoding.picodiploma.mysubmade.core.remoteres.MovieRes

object DataMapper {
    fun mapResponseToEntities(input: List<MovieRes>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                id = it.id,
                coverMovie = it.coverMovie,
                title = it.title,
                date = it.date,
                vote_average = it.vote_average,
                desc = it.desc,
                isFav = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                id = it.id,
                coverMovie = it.coverMovie,
                title = it.title,
                date = it.date,
                vote_average = it.vote_average,
                description = it.desc,
                isFavorite = it.isFav
            )
        }

    fun mapDomainToEntity(input: Movie) = MovieEntity(
        id = input.id,
        coverMovie = input.coverMovie,
        title = input.title,
        date = input.date,
        vote_average = input.vote_average,
        desc = input.description,
        isFav = input.isFavorite
    )
}
package com.doniapr.core.utils

import com.doniapr.core.data.source.local.entity.MovieEntity
import com.doniapr.core.data.source.remote.response.MovieResponse
import com.doniapr.core.domain.model.Genre
import com.doniapr.core.domain.model.Movie

object DataMapper {
    fun mapResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val tourism = MovieEntity(
                id = it.id,
                adult = it.adult,
                backdropPath = it.backdropPath,
                genres = it.genres.toString(),
                homepage = it.homepage,
                imdbId = it.imdbId,
                originalLanguage = it.originalLanguage,
                originalTitle = it.originalTitle,
                overview = it.overview,
                popularity = it.popularity,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                revenue = it.revenue,
                runtime = it.runtime,
                status = it.status,
                tagLine = it.tagLine,
                title = it.title,
                video = it.video,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
                isFavorite = false
            )
            movieList.add(tourism)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                id = it.id,
                adult = it.adult,
                backdropPath = it.backdropPath,
                genres = listOf(Genre(1, "1")),
                homepage = it.homepage,
                imdbId = it.imdbId,
                originalLanguage = it.originalLanguage,
                originalTitle = it.originalTitle,
                overview = it.overview,
                popularity = it.popularity,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                revenue = it.revenue,
                runtime = it.runtime,
                status = it.status,
                tagLine = it.tagLine,
                title = it.title,
                video = it.video,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Movie) = MovieEntity(
        id = input.id,
        adult = input.adult,
        backdropPath = input.backdropPath,
        genres = input.genres.toString(),
        homepage = input.homepage,
        imdbId = input.imdbId,
        originalLanguage = input.originalLanguage,
        originalTitle = input.originalTitle,
        overview = input.overview,
        popularity = input.popularity,
        posterPath = input.posterPath,
        releaseDate = input.releaseDate,
        revenue = input.revenue,
        runtime = input.runtime,
        status = input.status,
        tagLine = input.tagLine,
        title = input.title,
        video = input.video,
        voteAverage = input.voteAverage,
        voteCount = input.voteCount,
        isFavorite = input.isFavorite
    )
}
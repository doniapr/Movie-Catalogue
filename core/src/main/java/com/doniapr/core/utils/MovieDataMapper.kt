package com.doniapr.core.utils

import com.doniapr.core.data.source.local.entity.MovieEntity
import com.doniapr.core.data.source.local.entity.ReviewEntity
import com.doniapr.core.data.source.remote.response.MovieResponse
import com.doniapr.core.data.source.remote.response.ReviewResponse
import com.doniapr.core.domain.model.Genre
import com.doniapr.core.domain.model.Movie
import com.doniapr.core.domain.model.Review
import com.google.gson.Gson
import com.google.gson.internal.LinkedTreeMap

object MovieDataMapper {
    fun mapResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            var genre = ""
            if (it.genres != null && it.genres.isNotEmpty()) {
                genre = Gson().toJson(it.genres)
            }
            val movie = MovieEntity(
                id = it.id,
                adult = it.adult,
                backdropPath = it.backdropPath,
                genres = genre,
                homepage = it.homepage ?: "",
                imdbId = it.imdbId ?: "",
                originalLanguage = it.originalLanguage,
                originalTitle = it.originalTitle,
                overview = it.overview,
                popularity = it.popularity,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                revenue = it.revenue ?: 0,
                runtime = it.runtime ?: 0,
                status = it.status ?: "",
                tagLine = it.tagLine ?: "",
                title = it.title,
                video = it.video ?: false,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
                isFavorite = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapResponseToEntity(input: MovieResponse): MovieEntity {
        var genre = ""
        if (input.genres != null && input.genres.isNotEmpty()) {
            genre = Gson().toJson(input.genres)
        }
        return MovieEntity(
            id = input.id,
            adult = input.adult,
            backdropPath = input.backdropPath,
            genres = genre,
            homepage = input.homepage ?: "",
            imdbId = input.imdbId ?: "",
            originalLanguage = input.originalLanguage,
            originalTitle = input.originalTitle,
            overview = input.overview,
            popularity = input.popularity,
            posterPath = input.posterPath,
            releaseDate = input.releaseDate,
            revenue = input.revenue ?: 0,
            runtime = input.runtime ?: 0,
            status = input.status ?: "",
            tagLine = input.tagLine ?: "",
            title = input.title,
            video = input.video ?: false,
            voteAverage = input.voteAverage,
            voteCount = input.voteCount,
            isFavorite = false
        )
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            val listGenre = ArrayList<Genre>()
            if (it.genres != null && it.genres?.isNotEmpty()!!) {
                listGenre.addAll(setUpGenre(it.genres!!))
            }
            Movie(
                id = it.id,
                adult = it.adult,
                backdropPath = it.backdropPath,
                genres = listGenre,
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

    fun mapEntityToDomain(input: MovieEntity): Movie {
        val listGenre = ArrayList<Genre>()
        if (input.genres != null && input.genres?.isNotEmpty()!!) {
            listGenre.addAll(setUpGenre(input.genres!!))
        }
        return Movie(
            id = input.id,
            adult = input.adult,
            backdropPath = input.backdropPath,
            genres = listGenre,
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

    fun mapDomainToEntity(input: Movie): MovieEntity {
        var genre = ""
        if (input.genres != null && input.genres.isNotEmpty()) {
            genre = Gson().toJson(input.genres)
        }
        return MovieEntity(
            id = input.id,
            adult = input.adult,
            backdropPath = input.backdropPath,
            genres = genre,
            homepage = input.homepage ?: "",
            imdbId = input.imdbId ?: "",
            originalLanguage = input.originalLanguage,
            originalTitle = input.originalTitle,
            overview = input.overview,
            popularity = input.popularity,
            posterPath = input.posterPath,
            releaseDate = input.releaseDate,
            revenue = input.revenue ?: 0,
            runtime = input.runtime ?: 0,
            status = input.status ?: "",
            tagLine = input.tagLine ?: "",
            title = input.title,
            video = input.video ?: false,
            voteAverage = input.voteAverage,
            voteCount = input.voteCount,
            isFavorite = input.isFavorite
        )
    }

    fun mapReviewResponsesToEntities(
        input: List<ReviewResponse>,
        catalogueId: Int
    ): List<ReviewEntity> {
        val reviewList = ArrayList<ReviewEntity>()
        input.map {
            val review = ReviewEntity(
                id = it.id,
                catalogueId = catalogueId,
                isMovie = true,
                author = it.author,
                content = it.content,
                url = it.url,
                createdAt = it.createdAt,
                updatedAt = it.updatedAt
            )
            reviewList.add(review)
        }
        return reviewList
    }

    fun mapReviewEntitiesToDomain(input: List<ReviewEntity>): List<Review> =
        input.map {
            Review(
                id = it.id,
                author = it.author,
                content = it.content,
                url = it.url,
                createdAt = it.createdAt,
                updatedAt = it.updatedAt
            )
        }

    private fun setUpGenre(genreStr: String): List<Genre> {
        val listGenre = ArrayList<Genre>()
        val obj = Gson().fromJson(genreStr, List::class.java)

        for (genreObjStr in obj) {
            val genreObj = genreObjStr as LinkedTreeMap<*, *>
            val genreId: Double = (genreObj["id"] ?: 0) as Double
            val genre = Genre(genreId.toInt(), (genreObj["name"] ?: "") as String)


            listGenre.add(genre)
        }
        return listGenre
    }
}
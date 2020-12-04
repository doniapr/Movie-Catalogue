package com.doniapr.core.utils

import com.doniapr.core.data.source.local.entity.MovieEntity
import com.doniapr.core.data.source.local.entity.TvShowEntity
import com.doniapr.core.data.source.remote.response.MovieResponse
import com.doniapr.core.data.source.remote.response.TvShowResponse
import com.doniapr.core.domain.model.Genre
import com.doniapr.core.domain.model.Movie
import com.doniapr.core.domain.model.TvShow
import com.google.gson.Gson
import com.google.gson.internal.LinkedTreeMap

object DataMapper {
    fun mapResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            var genre = ""
            if (it.genres != null && it.genres.isNotEmpty()){
                genre = Gson().toJson(it.genres)
            }
            val movie = MovieEntity(
                id = it.id,
                adult = it.adult,
                backdropPath = it.backdropPath,
                genres = genre,
                homepage = it.homepage?: "",
                imdbId = it.imdbId?: "",
                originalLanguage = it.originalLanguage,
                originalTitle = it.originalTitle,
                overview = it.overview,
                popularity = it.popularity,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                revenue = it.revenue?: 0,
                runtime = it.runtime?: 0,
                status = it.status?: "",
                tagLine = it.tagLine?: "",
                title = it.title,
                video = it.video?: false,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
                isFavorite = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapResponsesToEntitiesTv(input: List<TvShowResponse>): List<TvShowEntity> {
        val tvShowList = ArrayList<TvShowEntity>()
        input.map {
            var genre = ""
            var episodeRunTime = ""
            if (it.genres != null && it.genres.isNotEmpty()) {
                genre = Gson().toJson(it.genres)
            }
            if (it.episodeRunTime != null && it.episodeRunTime.isNotEmpty()) {
                episodeRunTime = it.episodeRunTime.joinToString()
            }
            val tvShow = TvShowEntity(
                    id = it.id,
                    backdropPath = it.backdropPath,
                    episodeRunTime = episodeRunTime,
                    genres = genre,
                    firstAirDate = it.firstAirDate,
                    homepage = it.homepage ?: "",
                    inProduction = it.inProduction,
                    lastAirDate = it.lastAirDate ?: "",
                    name = it.name,
                    numberOfEpisodes = it.numberOfEpisodes,
                    numberOfSeasons = it.numberOfSeasons,
                    originalLanguage = it.originalLanguage,
                    originalName = it.originalName,
                    overview = it.overview,
                    popularity = it.popularity,
                    posterPath = it.posterPath,
                    status = it.status ?: "",
                    tagLine = it.tagLine ?: "",
                    type = it.type,
                    voteAverage = it.voteAverage,
                    voteCount = it.voteCount,
                    isFavorite = false
            )
            tvShowList.add(tvShow)
        }
        return tvShowList
    }

    fun mapResponseToEntity(input: MovieResponse): MovieEntity {
            var genre = ""
            if (input.genres != null && input.genres.isNotEmpty()){
                genre = Gson().toJson( input.genres)
            }
            return MovieEntity(
                    id = input.id,
                    adult = input.adult,
                    backdropPath = input.backdropPath,
                    genres = genre,
                    homepage = input.homepage?: "",
                    imdbId = input.imdbId?: "",
                    originalLanguage = input.originalLanguage,
                    originalTitle = input.originalTitle,
                    overview = input.overview,
                    popularity = input.popularity,
                    posterPath = input.posterPath,
                    releaseDate = input.releaseDate,
                    revenue = input.revenue?: 0,
                    runtime = input.runtime?: 0,
                    status = input.status?: "",
                    tagLine = input.tagLine?: "",
                    title = input.title,
                    video = input.video?: false,
                    voteAverage = input.voteAverage,
                    voteCount = input.voteCount,
                    isFavorite = false
            )
    }

    fun mapResponseToEntityTv(input: TvShowResponse): TvShowEntity {
        var genre = ""
        var episodeRunTime = ""
        if (input.genres != null && input.genres.isNotEmpty()){
            genre = Gson().toJson(input.genres)
        }
        if (input.genres != null && input.genres.isNotEmpty()){
            episodeRunTime = input.episodeRunTime.joinToString()
        }
        return TvShowEntity(
                id = input.id,
                backdropPath = input.backdropPath,
                episodeRunTime = episodeRunTime,
                genres = genre,
                firstAirDate = input.firstAirDate,
                homepage = input.homepage ?: "",
                inProduction = input.inProduction,
                lastAirDate = input.lastAirDate ?: "",
                name = input.name,
                numberOfEpisodes = input.numberOfEpisodes,
                numberOfSeasons = input.numberOfSeasons,
                originalLanguage = input.originalLanguage,
                originalName = input.originalName,
                overview = input.overview,
                popularity = input.popularity,
                posterPath = input.posterPath,
                status = input.status ?: "",
                tagLine = input.tagLine ?: "",
                type = input.type,
                voteAverage = input.voteAverage,
                voteCount = input.voteCount,
                isFavorite = false
        )
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            val listGenre = ArrayList<Genre>()
            if (it.genres != null && it.genres?.isNotEmpty()!!){
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

    fun mapEntitiesToDomainTv(input: List<TvShowEntity>): List<TvShow> =
            input.map {
                val listGenre = ArrayList<Genre>()
                if (it.genres != null && it.genres?.isNotEmpty()!!){
                    listGenre.addAll(setUpGenre(it.genres!!))
                }
                TvShow(
                        id = it.id,
                        backdropPath = it.backdropPath,
                        episodeRunTime = it.episodeRunTime?:"",
                        genres = listGenre,
                        firstAirDate = it.firstAirDate,
                        homepage = it.homepage ?: "",
                        inProduction = it.inProduction?:false,
                        lastAirDate = it.lastAirDate ?: "",
                        name = it.name,
                        numberOfEpisodes = it.numberOfEpisodes?:0,
                        numberOfSeasons = it.numberOfSeasons?:0,
                        originalLanguage = it.originalLanguage,
                        originalName = it.originalName,
                        overview = it.overview,
                        popularity = it.popularity,
                        posterPath = it.posterPath,
                        status = it.status ?: "",
                        tagLine = it.tagLine ?: "",
                        type = it.type,
                        voteAverage = it.voteAverage,
                        voteCount = it.voteCount,
                        isFavorite = false
                )
            }

    fun mapEntityToDomain(input: MovieEntity): Movie {
            val listGenre = ArrayList<Genre>()
            if (input.genres != null && input.genres?.isNotEmpty()!!){
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

    fun mapEntityToDomainTv(input: TvShowEntity): TvShow {
        val listGenre = ArrayList<Genre>()
        if (input.genres != null && input.genres?.isNotEmpty()!!){
            listGenre.addAll(setUpGenre(input.genres!!))
        }
        return TvShow(
                id = input.id,
                backdropPath = input.backdropPath,
                episodeRunTime = input.episodeRunTime?: "",
                genres = listGenre,
                firstAirDate = input.firstAirDate,
                homepage = input.homepage ?: "",
                inProduction = input.inProduction?: false,
                lastAirDate = input.lastAirDate ?: "",
                name = input.name,
                numberOfEpisodes = input.numberOfEpisodes?: 0,
                numberOfSeasons = input.numberOfSeasons?: 0,
                originalLanguage = input.originalLanguage,
                originalName = input.originalName,
                overview = input.overview,
                popularity = input.popularity,
                posterPath = input.posterPath,
                status = input.status ?: "",
                tagLine = input.tagLine ?: "",
                type = input.type,
                voteAverage = input.voteAverage,
                voteCount = input.voteCount,
                isFavorite = false
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

    private fun setUpGenre(genreStr: String): List<Genre> {
        val listGenre = ArrayList<Genre>()
        val obj = Gson().fromJson(genreStr, List::class.java)

        for (genreObjStr in obj){
            val genreObj = genreObjStr as LinkedTreeMap<*, *>
            val genreId: Double = (genreObj["id"]?:0) as Double
            val genre = Genre(genreId.toInt(), (genreObj["name"] ?:"") as String)


            listGenre.add(genre)
        }
        return listGenre
    }
}
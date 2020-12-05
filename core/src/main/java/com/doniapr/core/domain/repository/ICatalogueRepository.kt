package com.doniapr.core.domain.repository

import com.doniapr.core.data.Resource
import com.doniapr.core.domain.model.Movie
import com.doniapr.core.domain.model.Review
import com.doniapr.core.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

interface ICatalogueRepository {
    fun getNowPlayingMovie(): Flow<Resource<List<Movie>>>

    fun getDetailMovie(id: String): Flow<Resource<Movie>>

    fun getMovieReview(id: String): Flow<Resource<List<Review>>>

    fun searchMovie(query: String): Flow<Resource<List<Movie>>>

    suspend fun setFavoriteMovie(movie: Movie, newState: Boolean)

    fun getOnAirTv(): Flow<Resource<List<TvShow>>>

    fun getDetailTv(id: String): Flow<Resource<TvShow>>

    fun getTvReview(id: String): Flow<Resource<List<Review>>>

    fun searchTvShow(query: String): Flow<Resource<List<TvShow>>>
}
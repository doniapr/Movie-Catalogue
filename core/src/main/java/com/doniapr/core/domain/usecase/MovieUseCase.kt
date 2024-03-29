package com.doniapr.core.domain.usecase

import com.doniapr.core.data.Resource
import com.doniapr.core.domain.model.Movie
import com.doniapr.core.domain.model.Review
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getNowPlayingMovie(): Flow<Resource<List<Movie>>>
    fun getDetailMovie(id: String): Flow<Resource<Movie>>
    fun getMovieReview(id: String): Flow<Resource<List<Review>>>
    fun setFavoriteMovie(movie: Movie, newState: Boolean)
    fun getFavoriteMovie(): Flow<List<Movie>>

}
package com.doniapr.core.domain.repository

import com.doniapr.core.data.Resource
import com.doniapr.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface ICatalogueRepository {
    fun getNowPlayingMovie(): Flow<Resource<List<Movie>>>

    fun getDetailMovie(id: String): Flow<Resource<Movie>>

    fun getMovieReview(id: String): Flow<Resource<List<Review>>>
}
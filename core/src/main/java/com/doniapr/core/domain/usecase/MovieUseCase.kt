package com.doniapr.core.domain.usecase

import com.doniapr.core.data.Resource
import com.doniapr.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getNowPlayingMovie(): Flow<Resource<List<Movie>>>
    fun getDetailMovie(): Flow<Resource<Movie>>
}
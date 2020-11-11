package com.doniapr.core.domain.usecase

import com.doniapr.core.data.Resource
import com.doniapr.core.domain.model.Movie
import com.doniapr.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieInteractor(private val movieRepository: IMovieRepository): MovieUseCase {
    override fun getNowPlayingMovie(): Flow<Resource<List<Movie>>> = movieRepository.getNowPlayingMovie()

    override fun getDetailMovie(): Flow<Resource<Movie>> = movieRepository.getDetailMovie()
}
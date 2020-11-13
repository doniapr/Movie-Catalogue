package com.doniapr.core.domain.usecase

import com.doniapr.core.data.Resource
import com.doniapr.core.domain.model.Movie
import com.doniapr.core.domain.repository.ICatalogueRepository
import kotlinx.coroutines.flow.Flow

class MovieInteractor(private val catalogueRepository: ICatalogueRepository): MovieUseCase {
    override fun getNowPlayingMovie(): Flow<Resource<List<Movie>>> = catalogueRepository.getNowPlayingMovie()

    override fun getDetailMovie(): Flow<Resource<Movie>> = catalogueRepository.getDetailMovie()
}
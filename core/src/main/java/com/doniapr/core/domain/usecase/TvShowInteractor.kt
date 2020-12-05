package com.doniapr.core.domain.usecase

import com.doniapr.core.data.Resource
import com.doniapr.core.domain.model.Review
import com.doniapr.core.domain.model.TvShow
import com.doniapr.core.domain.repository.ICatalogueRepository
import kotlinx.coroutines.flow.Flow

class TvShowInteractor(private val catalogueRepository: ICatalogueRepository): TvShowUseCase {
    override fun getOnAirTv(): Flow<Resource<List<TvShow>>> = catalogueRepository.getOnAirTv()

    override fun getDetailTvShow(id: String): Flow<Resource<TvShow>> = catalogueRepository.getDetailTv(id)

    override fun getTvShowReview(id: String): Flow<Resource<List<Review>>> = catalogueRepository.getTvReview(id)

    override fun searchTvShow(query: String): Flow<Resource<List<TvShow>>> = catalogueRepository.searchTvShow(query)
    override fun setFavoriteTvShow(tvShow: TvShow, newState: Boolean) {
        catalogueRepository.setFavoriteTvShow(tvShow, newState)
    }
}
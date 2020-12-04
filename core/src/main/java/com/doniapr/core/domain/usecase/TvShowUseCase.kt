package com.doniapr.core.domain.usecase

import com.doniapr.core.data.Resource
import com.doniapr.core.domain.model.Review
import com.doniapr.core.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

interface TvShowUseCase {
    fun getOnAirTv(): Flow<Resource<List<TvShow>>>
    fun getDetailTvShow(id: String): Flow<Resource<TvShow>>
    fun getTvShowReview(id:String): Flow<Resource<List<Review>>>
    fun searchTvShow(query: String): Flow<Resource<List<TvShow>>>

}
package com.doniapr.moviecatalogue.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.doniapr.core.data.Resource
import com.doniapr.core.domain.model.Review
import com.doniapr.core.domain.model.TvShow
import com.doniapr.core.domain.usecase.TvShowUseCase

class DetailTvShowViewModel(private val tvShowUseCase: TvShowUseCase) : ViewModel() {
    var tvShow: LiveData<Resource<TvShow>> = MutableLiveData()

    var reviews: LiveData<Resource<List<Review>>> = MutableLiveData()

    fun setTvShow(id: String) {
        tvShow = tvShowUseCase.getDetailTvShow(id).asLiveData()

        reviews = tvShowUseCase.getTvShowReview(id).asLiveData()
    }

    fun changeFavoriteState(tvShow: TvShow, state: Boolean) {
        tvShowUseCase.setFavoriteTvShow(tvShow, state)

    }
}
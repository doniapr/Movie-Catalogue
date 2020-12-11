package com.doniapr.moviecatalogue.favorite.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.doniapr.core.domain.usecase.TvShowUseCase

class TvShowFavoriteViewModel(tvShowUseCase: TvShowUseCase) : ViewModel() {
    val tvShows = tvShowUseCase.getFavoriteTvShow().asLiveData()
}
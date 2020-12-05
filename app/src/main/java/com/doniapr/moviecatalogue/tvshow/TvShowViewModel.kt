package com.doniapr.moviecatalogue.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.doniapr.core.domain.usecase.TvShowUseCase

class TvShowViewModel(tvShowUseCase: TvShowUseCase): ViewModel() {
    val tvShows = tvShowUseCase.getOnAirTv().asLiveData()
}
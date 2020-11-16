package com.doniapr.moviecatalogue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.doniapr.core.domain.usecase.MovieUseCase

class MainViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val movies = movieUseCase.getNowPlayingMovie().asLiveData()
}
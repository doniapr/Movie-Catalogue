package com.doniapr.moviecatalogue.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.doniapr.core.domain.usecase.MovieUseCase

class MovieViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val movies = movieUseCase.getNowPlayingMovie().asLiveData()
}
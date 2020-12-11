package com.doniapr.moviecatalogue.favorite.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.doniapr.core.domain.model.Movie
import com.doniapr.core.domain.usecase.MovieUseCase

class MovieFavoriteViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val movies: LiveData<List<Movie>> = movieUseCase.getFavoriteMovie().asLiveData()

}
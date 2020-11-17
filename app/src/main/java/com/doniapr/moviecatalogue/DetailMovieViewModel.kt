package com.doniapr.moviecatalogue

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.doniapr.core.data.Resource
import com.doniapr.core.domain.model.Movie
import com.doniapr.core.domain.usecase.MovieUseCase

class DetailMovieViewModel(private val movieUseCase: MovieUseCase): ViewModel() {
    var movie: LiveData<Resource<Movie>> = MutableLiveData<Resource<Movie>>()

    fun setMovie(id: String){
        movie = movieUseCase.getDetailMovie(id).asLiveData()
    }
}
package com.doniapr.moviecatalogue.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.doniapr.core.data.Resource
import com.doniapr.core.domain.model.Movie
import com.doniapr.core.domain.model.Review
import com.doniapr.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailMovieViewModel(private val movieUseCase: MovieUseCase): ViewModel() {
    var movie: LiveData<Resource<Movie>> = MutableLiveData()

    var reviews: LiveData<Resource<List<Review>>> = MutableLiveData()

    fun setMovie(id: String){
        movie = movieUseCase.getDetailMovie(id).asLiveData()

        reviews = movieUseCase.getMovieReview(id).asLiveData()
    }

    fun changeFavoriteState(movie: Movie, state: Boolean){
        movieUseCase.setFavoriteMovie(movie, state)
    }
}
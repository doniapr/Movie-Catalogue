package com.doniapr.core.domain.usecase

import com.doniapr.core.data.Resource
import com.doniapr.core.domain.model.Movie
import com.doniapr.core.domain.model.Review
import com.doniapr.core.domain.repository.ICatalogueRepository
import kotlinx.coroutines.flow.Flow

class MovieInteractor(private val catalogueRepository: ICatalogueRepository) : MovieUseCase {
    override fun getNowPlayingMovie(): Flow<Resource<List<Movie>>> =
        catalogueRepository.getNowPlayingMovie()

    override fun getDetailMovie(id: String): Flow<Resource<Movie>> =
        catalogueRepository.getDetailMovie(id)

    override fun getMovieReview(id: String): Flow<Resource<List<Review>>> =
        catalogueRepository.getMovieReview(id)

    override fun setFavoriteMovie(movie: Movie, newState: Boolean) {
        catalogueRepository.setFavoriteMovie(movie, newState)
    }

    override fun getFavoriteMovie(): Flow<List<Movie>> = catalogueRepository.getFavoriteMovie()
}
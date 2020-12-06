package com.doniapr.core.data.source.local

import com.doniapr.core.data.source.local.entity.MovieEntity
import com.doniapr.core.data.source.local.entity.ReviewEntity
import com.doniapr.core.data.source.local.entity.TvShowEntity
import com.doniapr.core.data.source.local.room.MovieDao
import com.doniapr.core.data.source.local.room.TvShowDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movieDao: MovieDao, private val tvShowDao: TvShowDao) {
    fun getAllMovie(): Flow<List<MovieEntity>> = movieDao.getAllMovie()

    fun getDetailMovie(id: String): Flow<MovieEntity> = movieDao.getDetailMovie(id)

    fun getFavoriteMovie(): Flow<List<MovieEntity>> = movieDao.getFavoriteMovie()

    suspend fun insertMovie(movieList: List<MovieEntity>) = movieDao.insertMovie(movieList)

    suspend fun updateDetailMovie(movie: MovieEntity) = movieDao.updateDetailMovie(movie)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        movieDao.updateFavoriteMovie(movie)
    }

    suspend fun insertMovieReview(listReview: List<ReviewEntity>) =
        movieDao.insertReview(listReview)

    fun getMovieReview(id: Int): Flow<List<ReviewEntity>> = movieDao.getMovieReview(id)


    fun getAllTvShow(): Flow<List<TvShowEntity>> = tvShowDao.getAllTvShow()

    fun getDetailTvShow(id: String): Flow<TvShowEntity> = tvShowDao.getDetailTv(id)

    fun getFavoriteTvShow(): Flow<List<TvShowEntity>> = tvShowDao.getFavoriteTvShow()

    suspend fun insertTvShow(tvShowList: List<TvShowEntity>) = tvShowDao.insertTvShow(tvShowList)

    suspend fun updateDetailTvShoe(tvShow: TvShowEntity) = tvShowDao.updateDetailMTvShow(tvShow)

    fun setFavoriteTvShow(tvShow: TvShowEntity, newState: Boolean) {
        tvShow.isFavorite = newState
        tvShowDao.updateFavoriteTvShow(tvShow)
    }

    suspend fun insertTvReview(listReview: List<ReviewEntity>) = tvShowDao.insertReview(listReview)

    fun getTvReview(id: Int): Flow<List<ReviewEntity>> = tvShowDao.getTvReview(id)

}
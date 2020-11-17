package com.doniapr.core.data.source.local

import com.doniapr.core.data.source.local.entity.MovieEntity
import com.doniapr.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movieDao: MovieDao) {
    fun getAllMovie(): Flow<List<MovieEntity>> = movieDao.getAllMovie()

    fun getDetailMovie(id: String): Flow<MovieEntity> = movieDao.getDetailMovie(id)

    fun getFavoriteMovie(): Flow<List<MovieEntity>> = movieDao.getFavoriteMovie()

    suspend fun insertMovie(movieList: List<MovieEntity>) = movieDao.insertMovie(movieList)

    suspend fun updateDetailMovie(movie: MovieEntity) = movieDao.updateDetailMovie(movie)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean){
        movie.isFavorite = newState
        movieDao.updateFavoriteMovie(movie)
    }
}
package com.doniapr.core.data.source.local.room

import androidx.room.*
import com.doniapr.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie")
    fun getAllMovie(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie where id=:id")
    fun getDetailMovie(id: String): Flow<MovieEntity>

    @Query("SELECT * FROM movie where isFavorite = 1")
    fun getFavoriteMovie(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movies: List<MovieEntity>)

    @Update
    suspend fun updateDetailMovie(movie: MovieEntity)

    @Update
    fun updateFavoriteMovie(movie: MovieEntity)
}
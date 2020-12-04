package com.doniapr.core.data.source.local.room

import androidx.room.*
import com.doniapr.core.data.source.local.entity.MovieEntity
import com.doniapr.core.data.source.local.entity.ReviewEntity
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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReview(reviews: List<ReviewEntity>)

    @Query("SELECT * FROM review WHERE catalogue_id = :id AND is_movie = 1")
    fun getMovieReview(id: Int): Flow<List<ReviewEntity>>
}
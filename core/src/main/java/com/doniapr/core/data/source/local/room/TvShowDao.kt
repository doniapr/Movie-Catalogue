package com.doniapr.core.data.source.local.room

import androidx.room.*
import com.doniapr.core.data.source.local.entity.MovieEntity
import com.doniapr.core.data.source.local.entity.ReviewEntity
import com.doniapr.core.data.source.local.entity.TvShowEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TvShowDao {
    @Query("SELECT * FROM tvshow")
    fun getAllTvShow(): Flow<List<TvShowEntity>>

    @Query("SELECT * FROM tvshow where id=:id")
    fun getDetailTv(id: String): Flow<TvShowEntity>

    @Query("SELECT * FROM tvshow where isFavorite = 1")
    fun getFavoriteTvShow(): Flow<List<TvShowEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShow(tvShows: List<TvShowEntity>)

    @Update
    suspend fun updateDetailMTvShow(tvShow: TvShowEntity)

    @Update
    fun updateFavoriteTvShow(tvShow: TvShowEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReview(reviews: List<ReviewEntity>)

    @Query("SELECT * FROM review WHERE catalogue_id = :id AND is_movie = 0")
    fun getTvReview(id: Int): Flow<List<ReviewEntity>>
}
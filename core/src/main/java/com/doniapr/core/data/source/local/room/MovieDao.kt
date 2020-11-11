package com.doniapr.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Query
import com.doniapr.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie")
    fun getAllMovie(): Flow<List<MovieEntity>>
}
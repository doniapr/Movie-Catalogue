package com.doniapr.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.doniapr.core.data.source.local.entity.MovieEntity
import com.doniapr.core.data.source.local.entity.ReviewEntity
import com.doniapr.core.data.source.local.entity.TvShowEntity

@Database(entities = [MovieEntity::class, TvShowEntity::class, ReviewEntity::class], version = 1, exportSchema = false)
abstract class CatalogueDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    abstract fun tvShowDao(): TvShowDao
}

package com.doniapr.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "review")
data class ReviewEntity(
        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "id")
        var id: String,

        @ColumnInfo(name = "catalogue_id")
        var catalogueId: Int,

        @ColumnInfo(name = "is_movie")
        var isMovie: Boolean,

        @ColumnInfo(name = "author")
        val author: String,

        @ColumnInfo(name = "content")
        val content: String,

        @ColumnInfo(name = "url")
        val url: String,

        @ColumnInfo(name = "created_at")
        val createdAt: String,

        @ColumnInfo(name = "updated_at")
        val updatedAt: String

)
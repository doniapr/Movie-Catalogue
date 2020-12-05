package com.doniapr.core.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
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

) : Parcelable
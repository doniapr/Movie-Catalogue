package com.doniapr.core.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.doniapr.core.domain.model.Genre
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "movie")
data class MovieEntity (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "adult")
    var adult: Boolean,

    @ColumnInfo(name = "backdropPath")
    var backdropPath: String,

    @ColumnInfo(name = "genres")
    var genres: List<Genre>,

    @ColumnInfo(name = "homepage")
    var homepage: String,

    @ColumnInfo(name = "imdbId")
    var imdbId: String,

    @ColumnInfo(name = "originalLanguage")
    var originalLanguage: String,

    @ColumnInfo(name = "originalTitle")
    var originalTitle: String,

    @ColumnInfo(name = "overview")
    var overview: String,

    @ColumnInfo(name = "popularity")
    var popularity: Double,

    @ColumnInfo(name = "posterPath")
    var posterPath: String,

    @ColumnInfo(name = "releaseDate")
    var releaseDate: String,

    @ColumnInfo(name = "revenue")
    var revenue: Int,

    @ColumnInfo(name = "runtime")
    var runtime: Int,

    @ColumnInfo(name = "status")
    var status: String,

    @ColumnInfo(name = "tagLine")
    var tagLine: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "video")
    var video: Boolean,

    @ColumnInfo(name = "voteAverage")
    var voteAverage: Double,

    @ColumnInfo(name = "voteCount")
    var voteCount: Int,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false

) : Parcelable
package com.doniapr.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tvshow")
data class TvShowEntity(
        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "id")
        var id: Int,

        @ColumnInfo(name = "backdropPath")
        var backdropPath: String,

        @ColumnInfo(name = "episodeRunTime")
        var episodeRunTime: String?,

        @ColumnInfo(name = "genres")
        var genres: String?,

        @ColumnInfo(name = "firstAirDate")
        var firstAirDate: String,

        @ColumnInfo(name = "homepage")
        var homepage: String?,

        @ColumnInfo(name = "inProduction")
        var inProduction: Boolean?,

        @ColumnInfo(name = "lastAirDate")
        var lastAirDate: String?,

        @ColumnInfo(name = "name")
        var name: String,

        @ColumnInfo(name = "numberOfEpisodes")
        var numberOfEpisodes: Int?,

        @ColumnInfo(name = "numberOfSeasons")
        var numberOfSeasons: Int?,

        @ColumnInfo(name = "originalLanguage")
        var originalLanguage: String,

        @ColumnInfo(name = "originalName")
        var originalName: String,

        @ColumnInfo(name = "overview")
        var overview: String,

        @ColumnInfo(name = "popularity")
        var popularity: Double,

        @ColumnInfo(name = "posterPath")
        var posterPath: String,

        @ColumnInfo(name = "status")
        var status: String?,

        @ColumnInfo(name = "tagLine")
        var tagLine: String?,

        @ColumnInfo(name = "type")
        var type: String?,

        @ColumnInfo(name = "voteAverage")
        var voteAverage: Double,

        @ColumnInfo(name = "voteCount")
        var voteCount: Int,

        @ColumnInfo(name = "isFavorite")
        var isFavorite: Boolean = false
)
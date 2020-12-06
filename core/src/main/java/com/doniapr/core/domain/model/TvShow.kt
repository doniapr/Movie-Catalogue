package com.doniapr.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShow(
        val backdropPath: String,
        val episodeRunTime: String,
        val genres: List<Genre>?,
        val firstAirDate: String,
        val homepage: String,
        val id: Int,
        val inProduction: Boolean,
        val lastAirDate: String,
        val name: String,
        val numberOfEpisodes: Int,
        val numberOfSeasons: Int,
        val originalLanguage: String,
        val originalName: String,
        val overview: String,
        val popularity: Double,
        val posterPath: String,
        val status: String,
        val tagLine: String,
        val type: String,
        val voteAverage: Double,
        val voteCount: Int,
        val isFavorite: Boolean
) : Parcelable
package com.doniapr.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListTvShowResponse (
    @field:SerializedName("results")
    val results: List<TvShowResponse>,

    @field:SerializedName("total_results")
    val totalResults: Int,

    @field:SerializedName("page")
    val page: Int,

    @field:SerializedName("total_pages")
    val totalPages: Int
)
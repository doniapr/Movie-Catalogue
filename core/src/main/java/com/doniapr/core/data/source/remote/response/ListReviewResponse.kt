package com.doniapr.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListReviewResponse(
        @field:SerializedName("results")
        val results: List<ReviewResponse>,

        @field:SerializedName("total_results")
        val totalResults: Int,

        @field:SerializedName("page")
        val page: Int,

        @field:SerializedName("total_pages")
        val totalPages: Int
)
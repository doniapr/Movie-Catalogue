package com.doniapr.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Review (
        val id: String,
        val author: String,
        val content: String,
        val url: String,
        val createdAt: String,
        val updatedAt: String
) : Parcelable
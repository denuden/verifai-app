package com.gmail.denuelle42.aiprompter.data.remote.models.fact_check

import androidx.annotation.Keep

@Keep
data class LinkMetaDataModel(
    val url: String,
    val finalUrl: String,
    val image: String?,
    val title: String?,
    val description: String?
)

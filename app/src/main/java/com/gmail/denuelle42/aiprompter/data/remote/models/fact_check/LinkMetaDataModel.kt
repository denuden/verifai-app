package com.gmail.denuelle42.aiprompter.data.remote.models.fact_check

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Keep
@Serializable
@Parcelize
data class LinkMetaDataModel(
    val url: String,
    val finalUrl: String,
    val image: String?,
    val title: String?,
    val description: String?
) : Parcelable

package com.gmail.denuelle42.aiprompter.data.remote.models

import androidx.annotation.Keep

@Keep
data class PaginationModel(
    val page : Int,
    val pageSize : Int,
)
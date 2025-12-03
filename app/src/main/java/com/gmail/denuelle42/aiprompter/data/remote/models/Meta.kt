package com.gmail.denuelle42.aiprompter.data.remote.models

import androidx.annotation.Keep

@Keep
data class Meta(
    val copyright: String? = null, // © 2025 Laravel API. All Rights Reserved.
    val author: String? = null, // Justin Kirk A. Nacor
    val jsonapi: Jsonapi? = null
)

@Keep
data class Jsonapi(
    val version: Any? = null, // null
    val build: Any? = null // null
)
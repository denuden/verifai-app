package com.gmail.denuelle42.aiprompter.data.remote.models

import androidx.annotation.Keep

@Keep
data class UserModel(
    val id: Int? = null, // 1
    val name: String? = null, // Justin Kirk Nacor
    val email: String? = null, // justinkirka.nacor@gmail.com
    val created_at: String? = null, // 2025-11-30T12:02:35+00:00
    val updated_at: String? = null // 2025-11-30T12:02:35+00:00
)
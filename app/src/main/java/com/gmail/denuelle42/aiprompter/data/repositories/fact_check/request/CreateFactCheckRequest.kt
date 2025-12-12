package com.gmail.denuelle42.aiprompter.data.repositories.fact_check.request

import androidx.annotation.Keep

@Keep
data class CreateFactCheckRequest(
 val statement : String,
)
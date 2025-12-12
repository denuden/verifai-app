package com.gmail.denuelle42.aiprompter.data.repositories.fact_check.response


import androidx.annotation.Keep
import com.gmail.denuelle42.aiprompter.data.remote.models.Meta
import com.gmail.denuelle42.aiprompter.data.remote.models.fact_check.FactCheckModel

@Keep
data class CreateFactCheckResponse(
    val meta: Meta? = null,
    val `data`: FactCheckModel? = null,
    val message: String? = null // Your fact-checks have been fetched successfully!
)
package com.gmail.denuelle42.aiprompter.data.repositories.fact_check.response


import androidx.annotation.Keep
import com.gmail.denuelle42.aiprompter.data.remote.models.Meta
import com.gmail.denuelle42.aiprompter.data.remote.models.fact_check.FactCheckModel

@Keep
data class ShowFactCheckResponse(
    val meta: Meta? = null,
    val total: Int? = null, // 1
    val per_page: Int? = null, // 10
    val current_page: Int? = null, // 1
    val total_pages: Int? = null, // 1
    val has_more_pages: Boolean? = null, // false
    val `data`: List<FactCheckModel?>? = null,
    val message: String? = null // Your fact-checks have been fetched successfully!
)
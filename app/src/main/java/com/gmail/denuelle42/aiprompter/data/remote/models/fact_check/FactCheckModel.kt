package com.gmail.denuelle42.aiprompter.data.remote.models.fact_check

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Keep
@Serializable
@Parcelize
data class FactCheckModel(
    val id: Int? = null, // 1
    val statement: String? = null, // 500 pesos is enough for noche buena
    val explanation: String? = null, // While the Department of Trade and Industry (DTI) claims a 500-peso Noche Buena is possible for a family of four with a pared-down menu, this claim has been met with skepticism and criticism, with many considering it unrealistic given rising food prices. The feasibility depends heavily on family size and the specific dishes included.
    val sources: List<LinkMetaDataModel ?>? = null,
    val verdict: String? = null, // misleading
    val created_at: String? = null, // 2025-12-01T01:23:00+00:00
    val updated_at: String? = null // 2025-12-01T01:23:00+00:00
) : Parcelable
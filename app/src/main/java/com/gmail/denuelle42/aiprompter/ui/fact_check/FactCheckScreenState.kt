package com.gmail.denuelle42.aiprompter.ui.fact_check

import com.gmail.denuelle42.aiprompter.data.remote.models.fact_check.LinkMetaDataModel
import com.gmail.denuelle42.aiprompter.data.repositories.fact_check.response.CreateFactCheckResponse

data class FactCheckScreenState(
    val textPrompt : String? = null,
    val textPromptError : String? = null,

    val createFactCheckResponse: CreateFactCheckResponse? = null,
    val isCreateFactCheckLoading : Boolean = false,
    val listOfPreviewSources : List<LinkMetaDataModel>? = null,

)
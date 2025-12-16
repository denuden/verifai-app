package com.gmail.denuelle42.aiprompter.ui.fact_check

import com.gmail.denuelle42.aiprompter.data.remote.models.fact_check.FactCheckModel
import com.gmail.denuelle42.aiprompter.data.remote.models.fact_check.LinkMetaDataModel
import com.gmail.denuelle42.aiprompter.data.repositories.fact_check.response.CreateFactCheckResponse

data class FactCheckScreenState(
    val textPrompt : String? = null,
    val textPromptError : String? = null,

    val createFactCheckResponse: CreateFactCheckResponse? = null,
    val isCreateFactCheckLoading : Boolean = false,
    val listOfPreviewSources : List<LinkMetaDataModel>? = null,

    val getAllFactCheckResponse : List<FactCheckModel> = emptyList(),
    val endReached : Boolean = false,
    val page : Int = 1,
    val getAllFactCheckError : String? = null,
    val isGetAllFactCheckLoading : Boolean = false

)
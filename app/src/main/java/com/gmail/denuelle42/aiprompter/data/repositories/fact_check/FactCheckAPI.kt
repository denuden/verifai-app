package com.gmail.denuelle42.aiprompter.data.repositories.fact_check

import com.gmail.denuelle42.aiprompter.data.repositories.fact_check.request.CreateFactCheckRequest
import com.gmail.denuelle42.aiprompter.data.repositories.fact_check.response.CreateFactCheckResponse
import com.gmail.denuelle42.aiprompter.data.repositories.fact_check.response.GetAllFactChecksResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface FactCheckAPI {
    @POST("v1/fact-checks/create")
    suspend fun createFactCheck(@Body request : CreateFactCheckRequest) : Response<CreateFactCheckResponse>

    @POST("v1/fact-checks")
    suspend fun getAllFactChecks() : Response<GetAllFactChecksResponse>
}
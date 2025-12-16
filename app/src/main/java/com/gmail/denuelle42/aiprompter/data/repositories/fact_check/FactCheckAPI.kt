package com.gmail.denuelle42.aiprompter.data.repositories.fact_check

import com.gmail.denuelle42.aiprompter.data.remote.models.PaginationModel
import com.gmail.denuelle42.aiprompter.data.repositories.fact_check.request.CreateFactCheckRequest
import com.gmail.denuelle42.aiprompter.data.repositories.fact_check.response.CreateFactCheckResponse
import com.gmail.denuelle42.aiprompter.data.repositories.fact_check.response.GetAllFactChecksResponse
import com.gmail.denuelle42.aiprompter.data.repositories.fact_check.response.ShowFactCheckResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface FactCheckAPI {
    @POST("v1/fact-checks/create")
    suspend fun createFactCheck(@Body request : CreateFactCheckRequest) : Response<CreateFactCheckResponse>

    @GET("v1/fact-checks")
    suspend fun getAllFactChecks(@Query("page") id : Int) : Response<GetAllFactChecksResponse>

    @GET("v1/fact-checks/{id}")
    suspend fun showFactCheck(@Path("id") id : Int) : Response<ShowFactCheckResponse>
}
package com.gmail.denuelle42.aiprompter.data.repositories.fact_check

import com.gmail.denuelle42.aiprompter.data.repositories.auth.request.LoginRequest
import com.gmail.denuelle42.aiprompter.data.repositories.auth.response.LoginResponse
import com.gmail.denuelle42.aiprompter.data.repositories.fact_check.request.CreateFactCheckRequest
import com.gmail.denuelle42.aiprompter.data.repositories.fact_check.response.CreateFactCheckResponse
import com.gmail.denuelle42.aiprompter.data.repositories.fact_check.response.GetAllFactChecksResponse
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.HttpException
import java.net.HttpURLConnection
import javax.inject.Inject

@ViewModelScoped
class FactCheckRepository @Inject constructor(
    private val factCheckAPI: FactCheckAPI,
) {
    suspend fun createFactCheck(request : CreateFactCheckRequest) : CreateFactCheckResponse {
        val response = factCheckAPI.createFactCheck(request)

        if(response.code() != HttpURLConnection.HTTP_CREATED){
            throw HttpException(response)
        }
        return response.body() ?: throw NullPointerException("Response data is empty")
    }


    suspend fun getAllFactChecks() : GetAllFactChecksResponse {
        val response = factCheckAPI.getAllFactChecks()

        if(response.code() != HttpURLConnection.HTTP_OK){
            throw HttpException(response)
        }
        return response.body() ?: throw NullPointerException("Response data is empty")
    }
}
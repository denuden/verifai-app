package com.gmail.denuelle42.aiprompter.domain.repositories.fact_check

import android.util.Log
import com.gmail.denuelle42.aiprompter.data.remote.models.PaginationModel
import com.gmail.denuelle42.aiprompter.data.remote.models.fact_check.FactCheckModel
import com.gmail.denuelle42.aiprompter.data.repositories.fact_check.FactCheckRepository
import com.gmail.denuelle42.aiprompter.data.repositories.fact_check.request.CreateFactCheckRequest
import com.gmail.denuelle42.aiprompter.data.repositories.fact_check.response.CreateFactCheckResponse
import com.gmail.denuelle42.aiprompter.data.repositories.fact_check.response.GetAllFactChecksResponse
import com.gmail.denuelle42.aiprompter.data.repositories.fact_check.response.ShowFactCheckResponse
import com.gmail.denuelle42.aiprompter.di.modules.IoDispatcher
import com.gmail.denuelle42.aiprompter.utils.LinkPreviewFetcher
import com.gmail.denuelle42.aiprompter.utils.extractUrl
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ViewModelScoped
class FactCheckUseCase @Inject constructor(
    private val factCheckRepository: FactCheckRepository,
    private val linkPreviewFetcher: LinkPreviewFetcher,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) {
    fun createFactCheck(request: CreateFactCheckRequest) : Flow<CreateFactCheckResponse> {
        return flow {
            val response = factCheckRepository.createFactCheck(request)

            val sources = response.data?.sources ?: emptyList()

            // 2. Parallel Fetching
            val enrichedSources = coroutineScope {
                sources.map { source ->
                    // Launch a parallel job for each source
                    // 'async' starts the work immediately without blocking the next iteration
                    async {
                        val cleanUrl = extractUrl(source?.url.orEmpty())

                        // Fetch the preview (this runs in parallel with others)
                        // If cleanUrl is empty, we just return null immediately
                        if (cleanUrl.orEmpty().isNotEmpty()) {
                            linkPreviewFetcher.fetch(cleanUrl.orEmpty())
                        } else {
                            null
                        }
                    }
                }.awaitAll() // 3. Wait here until ALL async jobs are done
            }

            // 4. Update the response with the new list (filtering out nulls if fetch failed)
            emit(response.copy(
                data = response.data?.copy(
                    sources = enrichedSources.filterNotNull()
                )
            ))
        }.flowOn(ioDispatcher)
    }

    /**
     * @param pageSize default is always 10 in API and cannot be changed
     * Paginated
     */
    suspend fun getAllFactChecks(page: Int, pageSize : Int = 10): Result<List<FactCheckModel>> {
        return try {
            val response = factCheckRepository.getAllFactChecks(page)
            val startingIndex = page * pageSize
            val total = response.total ?: 0

            if (response.data != null) {
                Result.success(response.data)
            } else {
                Result.success(emptyList())
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


    fun showFactCheck(id : Int) : Flow<ShowFactCheckResponse> {
        return flow {
            val response = factCheckRepository.showFactCheck(id)
            emit(response)
        }.flowOn(ioDispatcher)
    }
}
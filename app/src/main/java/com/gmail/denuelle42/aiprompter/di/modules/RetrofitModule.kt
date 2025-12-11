package com.gmail.denuelle42.aiprompter.di.modules

import com.gmail.denuelle42.aiprompter.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

//https://medium.com/@psarakisnick/clean-networking-with-retrofit-and-interceptor-in-kotlin-63a9ac85def2

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    /**
     * Adds Logging to Retrofit Requests and Responses in Logcat
     */
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY).apply {
            redactHeader("Authorization")
        }
    }

    /**
     * Provides custom httpclient for adding interceptors
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        tokenProvider: TokenProvider
    ): OkHttpClient {

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->

                // Get the latest token instantly from RAM
                val token = tokenProvider.getToken()

                val original = chain.request()

                /**
                 * If token exists, we attach the Authorization header.
                 *
                 * IMPORTANT:
                 * - This is a simple read (atomic)
                 * - No DataStore or disk access inside OkHttp thread
                 */

                val requestBuilder = original.newBuilder()
                    // 1. CRITICAL FIX FOR LARAVEL:
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json") // Good practice to ensure this is set too

                // 2. Add Token if exists
                if (!token.isNullOrEmpty()) {
                    requestBuilder.header("Authorization", "Bearer $token")
                }

                // Continue the request
                chain.proceed(requestBuilder.build())
            }
            // 30-60 seconds is safer for slow backends / cold starts
            .connectTimeout(45, TimeUnit.SECONDS)
            .readTimeout(45, TimeUnit.SECONDS)
            .writeTimeout(45, TimeUnit.SECONDS)
            .build()
    }


    /**
     * Provides Retrofit Client
     * asLenient() when dealing with APIs that might return slightly malformed JSON
     * failOnUnknown() when you want to ensure strict compliance with your data model, catching any unexpected fields in the JSON.
     * withNullSerialization() when you need to explicitly indicate that a property can be null in the JSON output, which might be necessary for certain APIs or data contracts.
     */
    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client).build()
    }
}
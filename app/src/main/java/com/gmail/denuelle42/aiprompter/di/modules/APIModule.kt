package com.gmail.denuelle42.aiprompter.di.modules

import com.gmail.denuelle42.aiprompter.data.repositories.auth.AuthAPI
import com.gmail.denuelle42.aiprompter.data.repositories.sample.SampleAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object APIModule {
    @Provides
    @Singleton
    fun provideSampleAPI(retrofit: Retrofit): SampleAPI {
        return retrofit.create(SampleAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthAPI(retrofit: Retrofit): AuthAPI {
        return retrofit.create(AuthAPI::class.java)
    }
}
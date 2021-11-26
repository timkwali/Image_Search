package com.timkwali.imagesearch.di

import com.timkwali.imagesearch.data.localdata.ImageDatabase
import com.timkwali.imagesearch.data.remote.ImageSearchApi
import com.timkwali.imagesearch.data.repository.ImageSearchRepository
import com.timkwali.imagesearch.domain.repository.ImageSearchRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ImageSearchApi {
        return retrofit.create(ImageSearchApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(apiService: ImageSearchApi, db: ImageDatabase): ImageSearchRepository {
        return ImageSearchRepositoryImpl(apiService, db)
    }

}
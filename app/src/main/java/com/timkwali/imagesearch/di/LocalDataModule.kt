package com.timkwali.imagesearch.di

import android.app.Application
import androidx.room.Room
import com.timkwali.imagesearch.data.localdata.ImageDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {

    @Provides
    @Singleton
    fun provideImageDatabase(app: Application): ImageDatabase {
        return Room.databaseBuilder(
            app,
            ImageDatabase::class.java,
            ImageDatabase.DATABASE_NAME
        ).build()
    }
}
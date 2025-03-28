package com.example.coin_application.di

import android.app.Application
import com.example.coin_application.MainApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {
    @Provides
    @Singleton
    fun provideMovieApplication(application: Application): MainApplication = application as MainApplication
}
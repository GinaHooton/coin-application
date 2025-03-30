package com.example.coin_application.di

import com.example.data.ErrorLoggerImpl
import com.example.data.mappers.CoinDtoToCoinDomainModelMapper
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton
import com.example.data.network.CoinService
import com.example.data.repository.CoinRepositoryImpl
import com.example.domain.ErrorLogger
import com.example.domain.repository.CoinRepository
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    private const val BASE_URL = "https://api.coinpaprika.com/"

    @Provides
    @Singleton
    fun provideCoinService(): CoinService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(CoinService::class.java)
    }

    @Provides
    @Singleton
    fun provideLog(): ErrorLogger {
        return ErrorLoggerImpl()
    }

    @Provides
    @Singleton
    fun provideCoinRepository(
        coinDtoToCoinDomainModelMapper: CoinDtoToCoinDomainModelMapper,
        coinService: CoinService,
        logger: ErrorLogger
    ): CoinRepository {
        return CoinRepositoryImpl(coinDtoToCoinDomainModelMapper, coinService, logger)
    }
}
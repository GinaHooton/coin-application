package com.example.coin_application.di

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
import com.example.domain.repository.CoinRepository
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    private const val BASE_URL = "https://api.coinpaprika.com/"

//    @Provides
//    @Singleton
//    fun provideJson(): Json {
//        return Json { ignoreUnknownKeys = true }
//    }

    @Provides
    @Singleton
    fun provideCoinService(): CoinService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(BASE_URL)
            .build()
            .create(CoinService::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(
        coinDtoToCoinDomainModelMapper: CoinDtoToCoinDomainModelMapper,
        coinService: CoinService,
    ): CoinRepository {
        return CoinRepositoryImpl(coinDtoToCoinDomainModelMapper, coinService)
    }
}
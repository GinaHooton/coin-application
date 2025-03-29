package com.example.data.network

import com.example.data.models.CoinDto
import retrofit2.http.GET

interface CoinService {
    @GET("v1/coins")
    suspend fun getCoins(): List<CoinDto>
}
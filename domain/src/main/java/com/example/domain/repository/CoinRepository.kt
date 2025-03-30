package com.example.domain.repository

import com.example.domain.models.CoinDomainModel

interface CoinRepository {
    suspend fun getCoins(): List<CoinDomainModel>
}
package com.example.domain.repository

import com.example.domain.models.CoinDomainModel

interface CoinRepository {
    fun getCoins(): List<CoinDomainModel>
}
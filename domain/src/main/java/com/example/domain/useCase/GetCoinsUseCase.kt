package com.example.domain.useCase

import com.example.domain.models.CoinDomainModel
import com.example.domain.repository.CoinRepository
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(private val coinRepository: CoinRepository) {
    suspend fun buildUseCase(): List<CoinDomainModel> {
        return coinRepository.getCoins().sortedBy { it.name }
//        return coinRepository.getCoins()
    }
}
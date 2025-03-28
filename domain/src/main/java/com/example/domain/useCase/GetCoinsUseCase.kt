package com.example.domain.useCase

import com.example.domain.models.CoinDomainModel
import com.example.domain.repository.CoinRepository
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(private val coinRepository: CoinRepository) {
    fun buildUseCase(): List<CoinDomainModel> {
        return coinRepository.getCoins()
    }
}
package com.example.data.repository

import com.example.data.mappers.CoinDtoToCoinDomainModelMapper
import com.example.data.network.CoinService
import com.example.domain.ErrorLogger
import com.example.domain.models.CoinDomainModel
import com.example.domain.repository.CoinRepository
import retrofit2.HttpException
import java.lang.RuntimeException
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val movieDtoToCoinDomainModelMapper: CoinDtoToCoinDomainModelMapper,
    private val coinService: CoinService,
    private val logger: ErrorLogger
) : CoinRepository {
    override suspend fun getCoins(): List<CoinDomainModel> {
        val coinDtoList = try {
            coinService.getCoins()
        } catch (e: HttpException) {
            logger.error("Repo implementation", "http exception: ", e)
            emptyList()
        } catch (e: RuntimeException) {
            logger.error("Repo implementation", "io exception: ", e)
            emptyList()
        }

        return coinDtoList.map { coinDto ->
            movieDtoToCoinDomainModelMapper.mapToDomain(coinDto)
        }
    }
}
package com.example.data.repository

import android.util.Log
import com.example.data.mappers.CoinDtoToCoinDomainModelMapper
import com.example.data.models.CoinDto
import com.example.data.network.CoinService
import com.example.domain.models.CoinDomainModel
import com.example.domain.repository.CoinRepository
import retrofit2.HttpException
import java.lang.RuntimeException
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val movieDtoToCoinDomainModelMapper: CoinDtoToCoinDomainModelMapper,
    private val coinService: CoinService
) : CoinRepository {

    val mockCoin = CoinDomainModel(
        id = "1",
        name = "Bitcoin",
        symbol = "BTC",
        rank = 1,
        isNew = false,
        isActive = true,
        type = "Crypto"
    )

    override suspend fun getCoins(): List<CoinDomainModel> {
        val coinDtoList = try {
            coinService.getCoins()
        } catch (e: HttpException) {
            Log.e("Repo implementation", "http exception: ", e)
            Log.e("TESTING", "http exception: ", e)
            emptyList()
        } catch (e: RuntimeException) {
            Log.e("TESTING", "io exception: ", e)
            emptyList()
        }

        return coinDtoList.map { coinDto ->
            movieDtoToCoinDomainModelMapper.mapToDomain(coinDto)
        }
//        return listOf(mockCoin)
    }
}
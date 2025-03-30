package com.example.data.repository

import com.example.data.mappers.CoinDtoToCoinDomainModelMapper
import com.example.data.models.CoinDto
import com.example.data.network.CoinService
import com.example.domain.ErrorLogger
import com.example.domain.models.CoinDomainModel
import org.junit.Assert.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import retrofit2.HttpException

class CoinRepositoryImplTest {
    @Mock
    private lateinit var coinService: CoinService

    @Mock
    private lateinit var coinDtoToCoinDomainModelMapper: CoinDtoToCoinDomainModelMapper

    @Mock
    private lateinit var errorLogger: ErrorLogger

    private lateinit var cut: CoinRepositoryImpl

    private val mockCoinDto = CoinDto(
        id = "id",
        name = "name",
        symbol = "symbol",
        rank = 1,
        isNew = false,
        isActive = false,
        type = "type"
    )

    private val expectedDomainModel = CoinDomainModel(
        id = "id",
        name = "name",
        symbol = "symbol",
        rank = 1,
        isNew = false,
        isActive = false,
        type = "type"
    )

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        whenever(
            errorLogger.error(
                any(),
                any(),
                any()
            )
        ).thenAnswer { println("Error message called") }
        cut = CoinRepositoryImpl(coinDtoToCoinDomainModelMapper, coinService, errorLogger)
    }

    @Test
    fun `Given successful network call with coins when get coins service called then return expected mapped coins`() {
        runTest {
            // Given
            whenever(coinDtoToCoinDomainModelMapper.mapToDomain(any())).thenReturn(
                expectedDomainModel
            )
            whenever(coinService.getCoins()).thenReturn(listOf(mockCoinDto))

            // When
            val result = cut.getCoins()

            // Then
            assertEquals(listOf(expectedDomainModel), result)
        }
    }

    @Test
    fun `Given unsuccessful network call (Runtime exception) with coins when get coins service called then return empty list`() {
        runTest {
            // Given
            val mockException = mock<RuntimeException>()
            whenever(coinService.getCoins()).thenThrow(mockException)

            // When
            val result = cut.getCoins()

            // Then
            assertEquals(emptyList<CoinDomainModel>(), result)
        }
    }

    @Test
    fun `Given unsuccessful network call (Http exception) with coins when get coins service called then return empty list`() {
        runTest {
            // Given
            val response = mock<HttpException>()
            whenever(coinService.getCoins()).thenThrow(response)

            // When
            val result = cut.getCoins()

            // Then
            assertEquals(emptyList<CoinDomainModel>(), result)
        }
    }
}
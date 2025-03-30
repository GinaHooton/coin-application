package com.example.domain.useCase

import com.example.domain.models.CoinDomainModel
import com.example.domain.repository.CoinRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class GetCoinsUseCaseTest {
    @Mock
    private lateinit var coinRepository: CoinRepository

    private lateinit var cut: GetCoinsUseCase

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
        cut = GetCoinsUseCase(coinRepository)
    }

    @Test
    fun `Given list of domain model coins when build use case called then return expected coins`() {
        runTest {
            // Given
            val domainModel = listOf(expectedDomainModel)
            whenever(coinRepository.getCoins()).thenReturn(domainModel)

            // When
            val result = cut.buildUseCase()

            // Then
            assertEquals(domainModel, result)
        }
    }
}
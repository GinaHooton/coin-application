package com.example.data.mappers

import com.example.data.models.CoinDto
import com.example.domain.models.CoinDomainModel
import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Before
import org.mockito.MockitoAnnotations

class CoinDtoToCoinDomainModelMapperTest {
    private lateinit var cut: CoinDtoToCoinDomainModelMapper

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        cut = CoinDtoToCoinDomainModelMapper()
    }

    @Test
    fun `Given data model when map to domain function called then return expected domain model`() {
        // Given
        val mockDto = CoinDto(
            id = "id",
            name = "name",
            symbol = "symbol",
            rank = 1,
            isNew = false,
            isActive = false,
            type = "type"
        )

        val expectedDomainModel = CoinDomainModel(
            id = "id",
            name = "name",
            symbol = "symbol",
            rank = 1,
            isNew = false,
            isActive = false,
            type = "type"
        )

        // When
        val result = cut.mapToDomain(mockDto)

        // Then
        assertEquals(expectedDomainModel, result)
    }
}
package com.example.coin_application.mapper

import com.example.coin_application.ui.mapper.CoinDomainModelToCoinUiModelMapper
import com.example.coin_application.ui.model.CoinUiModel
import com.example.domain.models.CoinDomainModel
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals

class CoinDomainModelToCoinUiModelMapperTest {
    private lateinit var cut: CoinDomainModelToCoinUiModelMapper

    val expectedUiModel = CoinUiModel(
        id = "id",
        name = "name",
        symbol = "symbol",
        rank = 1,
        isNew = false,
        isActive = false,
        type = "type"
    )

    val domainModel = CoinDomainModel(
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
        cut = CoinDomainModelToCoinUiModelMapper()
    }

    @Test
    fun `Given mapToPresentation with single domain model when mapping return expected ui model`() {
        // when
        val result = cut.mapToPresentation(listOf(domainModel))

        // then
        assertEquals(listOf(expectedUiModel), result)
    }
}
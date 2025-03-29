package com.example.data.mappers

import com.example.data.models.CoinDto
import com.example.domain.models.CoinDomainModel
import javax.inject.Inject

class CoinDtoToCoinDomainModelMapper @Inject constructor() {
    fun mapToDomain(coinDto: CoinDto): CoinDomainModel {
        return CoinDomainModel(
            id = coinDto.id,
            name = coinDto.name,
            symbol = coinDto.symbol,
            rank = coinDto.rank,
            isNew = coinDto.isNew,
            isActive = coinDto.isActive,
            type = coinDto.type
        )
    }
}
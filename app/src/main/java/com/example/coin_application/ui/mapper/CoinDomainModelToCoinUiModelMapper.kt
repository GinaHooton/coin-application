package com.example.coin_application.ui.mapper

import com.example.coin_application.ui.model.CoinUiModel
import com.example.domain.models.CoinDomainModel
import javax.inject.Inject

class CoinDomainModelToCoinUiModelMapper @Inject constructor() {
    fun mapToPresentation(coinDomainModelList: List<CoinDomainModel>): List<CoinUiModel> {
        return coinDomainModelList.map { coinDomainModel ->
            CoinUiModel(
                id = coinDomainModel.id,
                name = coinDomainModel.name,
                symbol = coinDomainModel.symbol,
                rank = coinDomainModel.rank,
                isNew = coinDomainModel.isNew,
                isActive = coinDomainModel.isActive,
                type = coinDomainModel.type
            )
        }
    }
}
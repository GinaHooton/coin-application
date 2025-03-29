package com.example.coin_application.ui.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coin_application.ui.mapper.CoinDomainModelToCoinUiModelMapper
import com.example.coin_application.ui.model.CoinUiModel
import com.example.domain.useCase.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase,
    private val coinDomainModelToCoinUiModelMapper: CoinDomainModelToCoinUiModelMapper
) : ViewModel() {
     val coinListLiveData: MutableLiveData<List<CoinUiModel>> = MutableLiveData(listOf())

    init {
        getCoin()
    }

    fun getCoin() {
        viewModelScope.launch {
            val coinDomainModelList = getCoinsUseCase.buildUseCase()
            val coinUiModelList =
                coinDomainModelToCoinUiModelMapper.mapToPresentation(coinDomainModelList)
            Log.d("TESTING", "in view model: $coinUiModelList")
            coinListLiveData.postValue(coinUiModelList)
        }
    }
}
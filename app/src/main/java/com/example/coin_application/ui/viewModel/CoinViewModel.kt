package com.example.coin_application.ui.viewModel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coin_application.ui.mapper.CoinDomainModelToCoinUiModelMapper
import com.example.coin_application.ui.model.CoinUiModel
import com.example.coin_application.ui.model.ScreenState
import com.example.domain.ErrorLogger
import com.example.domain.useCase.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class CoinViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase,
    private val coinDomainModelToCoinUiModelMapper: CoinDomainModelToCoinUiModelMapper,
    private val logger: ErrorLogger
) : ViewModel() {
    val coinListLiveData: MutableLiveData<List<CoinUiModel>> = MutableLiveData(null)
    val screenState: MutableLiveData<ScreenState> = MutableLiveData(ScreenState.LOADING)
    val errorType: MutableState<String> = mutableStateOf("")

    init {
        getCoin()
    }

    fun getCoin() {
        coinListLiveData.postValue(emptyList())
        screenState.postValue(ScreenState.LOADING)
        viewModelScope.launch {
            try {
                val coinDomainModelList = getCoinsUseCase.buildUseCase()
                val coinUiModelList =
                    coinDomainModelToCoinUiModelMapper.mapToPresentation(coinDomainModelList)
                coinListLiveData.postValue(coinUiModelList)
                Log.d("CoinViewModel", "getCoin: $coinUiModelList")
                screenState.postValue(ScreenState.SUCCESS)
            } catch (e: IOException) {
                screenState.postValue(ScreenState.ERROR)
                errorType.value = "IO exception"
                logger.error("CoinViewModel", "io exception get coins", e)
            } catch (e: HttpException) {
                screenState.postValue(ScreenState.ERROR)
                errorType.value = "HTTP exception"
                logger.error("CoinViewModel", "http exception", e)
            }

        }
    }
}
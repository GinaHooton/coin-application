package com.example.coin_application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.coin_application.ui.viewModel.CoinViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.runtime.livedata.observeAsState
import com.example.coin_application.ui.components.CoinPage
import com.example.coin_application.ui.components.ErrorPage
import com.example.coin_application.ui.components.LoadingPage
import com.example.coin_application.ui.model.ScreenState

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val viewModel: CoinViewModel by viewModels()
        viewModel.getCoin()
        setContent {
            val coinListState by viewModel.coinListLiveData.observeAsState(initial = null)
            val screenState by viewModel.screenState.observeAsState(initial = ScreenState.LOADING)
            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            ) { innerPadding ->
                when (screenState) {
                    ScreenState.LOADING -> {
                        LoadingPage(
                            modifier = Modifier.padding(innerPadding)
                        )
                    }

                    ScreenState.ERROR -> {
                        ErrorPage(
                            modifier = Modifier.padding(innerPadding),
                            retryEvent = { viewModel.getCoin() },
                            errorType = viewModel.errorType.value
                        )
                    }
                    else -> {
                        CoinPage(
                            modifier = Modifier.padding(innerPadding),
                            coinListState = coinListState!!
                        )
                    }
                }
            }
        }
    }
}
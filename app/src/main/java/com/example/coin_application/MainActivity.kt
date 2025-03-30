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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.coin_application.ui.theme.CoinapplicationTheme
import com.example.coin_application.ui.viewModel.CoinViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.runtime.livedata.observeAsState
import com.example.coin_application.ui.components.CoinPage
import com.example.coin_application.ui.components.ErrorPage
import com.example.coin_application.ui.components.LoadingPage

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val viewModel: CoinViewModel by viewModels()
        viewModel.getCoin()
        setContent {
            val coinListState by viewModel.coinListLiveData.observeAsState(initial = null)
            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            ) { innerPadding ->
                if (coinListState == null) {
                    LoadingPage(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
                else if (coinListState!!.isEmpty()) {
                    ErrorPage(
                        modifier = Modifier.padding(innerPadding),
                        retryEvent = { viewModel.getCoin() })
                } else {
                    CoinPage(
                        modifier = Modifier.padding(innerPadding),
                        coinListState = coinListState!!
                    )
                }
            }
        }
    }
}
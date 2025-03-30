package com.example.coin_application.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.coin_application.R

@Composable
fun ErrorPage(modifier: Modifier = Modifier, retryEvent: () -> Unit = {}) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.error_message),
            modifier = Modifier
                .padding(dimensionResource(R.dimen.default_padding))
                .fillMaxWidth()
        )
        Button(
            modifier = Modifier.padding(dimensionResource(R.dimen.default_padding)),
            onClick = retryEvent
        ) {
            Text(text = stringResource(R.string.retry_button))
        }
    }
}
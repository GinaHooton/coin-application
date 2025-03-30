package com.example.coin_application.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.coin_application.ui.model.CoinUiModel
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.ui.res.dimensionResource
import com.example.coin_application.R
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.ui.res.stringResource

@Composable
fun CoinPage(modifier: Modifier = Modifier, coinListState: List<CoinUiModel>) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.coin_list_heading),
            modifier = modifier.padding(dimensionResource(R.dimen.default_padding)),
            style = typography.headlineSmall,
        )
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(coinListState.size) { index ->
                CoinItem(coinUiModel = coinListState[index])
            }
        }
    }
}

@Composable
fun CoinItem(modifier: Modifier = Modifier, coinUiModel: CoinUiModel) {
    Text(
        text = "id ${coinUiModel.id} : ${coinUiModel.name}",
        modifier = modifier.padding(dimensionResource(R.dimen.default_padding)),
    )
    HorizontalDivider(thickness = dimensionResource(R.dimen.divider_thickness))
}

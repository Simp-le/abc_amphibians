package com.abc.amphibians.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.abc.amphibians.R
import com.abc.amphibians.ui.screens.HomeScreen
import com.abc.amphibians.ui.theme.AmphibiansTheme

@Composable
fun AmphibiansApp() {
    val amphibiansViewModel: AmphibiansViewModel = viewModel(factory = AmphibiansViewModel.Factory)

    Scaffold(modifier = Modifier.fillMaxSize(), topBar = { TopBar() }) { innerPadding ->
        HomeScreen(
            amphibiansUiState = amphibiansViewModel.amphibiansUiState,
            retryAction = amphibiansViewModel::getAmphibians,
            modifier = Modifier.fillMaxSize(),
            paddingValues = innerPadding
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(modifier: Modifier = Modifier) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.displayMedium
            )
        },
        modifier = modifier
    )
}


@Preview
@Composable
fun PreviewAmphibiansApp() {
    AmphibiansTheme {
        AmphibiansApp()
    }
}
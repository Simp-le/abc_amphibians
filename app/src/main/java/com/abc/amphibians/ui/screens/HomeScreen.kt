package com.abc.amphibians.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.abc.amphibians.R
import com.abc.amphibians.data.datasource.PreviewDataProvider
import com.abc.amphibians.data.model.Amphibian
import com.abc.amphibians.ui.AmphibiansUiState
import com.abc.amphibians.ui.theme.AmphibiansTheme

@Composable
fun HomeScreen(
    amphibiansUiState: AmphibiansUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(0.dp)
) {
    when (amphibiansUiState) {
        is AmphibiansUiState.Loading -> LoadingScreen(modifier = modifier.size(200.dp))

        is AmphibiansUiState.Success -> SuccessScreen(
            amphibianList = amphibiansUiState.amphibianList,
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(R.dimen.padding_small))
                .padding(vertical = dimensionResource(R.dimen.padding_medium)),
            paddingValues = paddingValues
        )

        else -> ErrorScreen(
            retryAction = retryAction,
            modifier = modifier.fillMaxSize()
        )
    }
}

/** The home screen displaying the loading message. */
@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier,
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

/** The home screen displaying error message with re-attempt button. */
@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = null
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}

/** The home screen displaying list of Amphibian object. */
@Composable
fun SuccessScreen(
    amphibianList: List<Amphibian>,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(0.dp)
) {
    /* TODO: Make it a LazyGrid if expanded window and a LazyColumn if compact window */
    LazyColumn(
        modifier = modifier,
        contentPadding = paddingValues,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
    ) {
        items(amphibianList, key = { amphibian -> amphibian.name }) { amphibian ->
            AmphibianItem(
                amphibian = amphibian,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

/** An item displaying an information about amphibian. */
@Composable
fun AmphibianItem(
    amphibian: Amphibian,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier, shape = MaterialTheme.shapes.medium) {
        Column(Modifier.padding(vertical = dimensionResource(R.dimen.padding_medium))) {
            Text(
                text = stringResource(R.string.amphibian_title, amphibian.name, amphibian.type),
                modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_medium)),
                style = MaterialTheme.typography.headlineLarge
            )
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(amphibian.photoUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = dimensionResource(R.dimen.padding_small))
                    .aspectRatio(1.5f),
                contentScale = ContentScale.Crop,
                error = painterResource(R.drawable.ic_broken_image),
                placeholder = painterResource(R.drawable.loading_img),
            )
            Text(
                text = amphibian.description,
                modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_medium)),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Justify
            )
        }
    }
}


@Preview
@Composable
fun PreviewAmphibianItem() {
    AmphibiansTheme {
        val amphibian = PreviewDataProvider.amphibian
        AmphibianItem(amphibian)
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    AmphibiansTheme {
        LoadingScreen(
            Modifier
                .fillMaxSize()
                .size(200.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    AmphibiansTheme {
        ErrorScreen({}, Modifier.fillMaxSize())
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    AmphibiansTheme {
        HomeScreen(
            amphibiansUiState = AmphibiansUiState.Success(PreviewDataProvider.getAmphibianList(10)),
            retryAction = { },
            paddingValues = PaddingValues(
                dimensionResource(R.dimen.padding_small)
            )
        )
    }
}
package me.fakhry.jetpackcomposemoviescatalogue.ui.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import me.fakhry.jetpackcomposemoviescatalogue.R
import me.fakhry.jetpackcomposemoviescatalogue.di.Injection
import me.fakhry.jetpackcomposemoviescatalogue.ui.ViewModelFactory
import me.fakhry.jetpackcomposemoviescatalogue.ui.common.UiState
import me.fakhry.jetpackcomposemoviescatalogue.ui.theme.JetpackComposeMoviesCatalogueTheme
import me.fakhry.jetpackcomposemoviescatalogue.ui.theme.Shapes

@Composable
fun DetailScreen(
    id: Long,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateBack: () -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getMovieById(id)
            }
            is UiState.Success -> {
                val data = uiState.data
                DetailContent(
                    poster = data.poster,
                    image = data.image,
                    title = data.title,
                    description = data.description,
                    onBackClick = navigateBack
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun DetailContent(
    poster: String,
    image: String,
    title: String,
    description: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colorStops = arrayOf(
        0.1f to Color.White,
        1f to Color.Transparent
    )
    Column {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = stringResource(R.string.back),
            modifier = Modifier
                .padding(16.dp)
                .clickable { onBackClick() }
        )
        Column {
            Box {
                AsyncImage(
                    model = poster,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .fillMaxWidth()
                        .height(300.dp),
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .background(Brush.horizontalGradient(colorStops = colorStops))
                )
                AsyncImage(
                    model = image,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(16.dp)
                        .height(260.dp)
                        .width(134.dp)
                        .clip(Shapes.small)
                )
            }
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h5.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                )
                Text(
                    text = stringResource(R.string.overview),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                    modifier = modifier
                        .padding(top = 32.dp)
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.body2,
                    modifier = modifier
                        .padding(top = 8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DetailScreenPreview() {
    JetpackComposeMoviesCatalogueTheme {
        DetailContent(
            poster = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/kuf6dutpsT0vSVehic3EZIqkOBt.jpg",
            image = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/kuf6dutpsT0vSVehic3EZIqkOBt.jpg",
            title = "Ant-man: Quantumania",
            description = "Super-Hero partners Scott Lang and Hope van Dyne, along with Hope\\'s parents Janet van Dyne and Hank Pym, and Scott\\'s daughter Cassie Lang, find themselves exploring the Quantum Realm, interacting with strange new creatures and embarking on an adventure that will push them beyond the limits of what they thought possible.",
            onBackClick = {}
        )
    }
}
package me.fakhry.jetpackcomposemoviescatalogue.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import me.fakhry.jetpackcomposemoviescatalogue.di.Injection
import me.fakhry.jetpackcomposemoviescatalogue.model.Movie
import me.fakhry.jetpackcomposemoviescatalogue.ui.ViewModelFactory
import me.fakhry.jetpackcomposemoviescatalogue.ui.common.UiState

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getMovies()
            }
            is UiState.Success -> {
                HomeContent(
                    movies = uiState.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun HomeContent(
    movies: List<Movie>,
    modifier: Modifier,
    navigateToDetail: (Long) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .padding(16.dp)
    ) {
        items(movies, key = { it.id }) { movie ->
            MovieListItem(
                title = movie.title,
                image = movie.image,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navigateToDetail(movie.id)
                    }
            )
        }
    }
}

@Composable
fun MovieListItem(
    title: String,
    image: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        AsyncImage(
            model = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .size(60.dp)
                .clip(CircleShape)
        )
        Text(
            text = title,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(start = 16.dp)
        )
    }
}
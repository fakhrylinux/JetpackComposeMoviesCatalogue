package me.fakhry.jetpackcomposemoviescatalogue.ui.screen.about

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import me.fakhry.jetpackcomposemoviescatalogue.R
import me.fakhry.jetpackcomposemoviescatalogue.ui.theme.JetpackComposeMoviesCatalogueTheme

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier
) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        AsyncImage(
            model = stringResource(R.string.image_profile),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .padding(16.dp)
                .clip(CircleShape),
        )
        Text(
            text = stringResource(R.string.my_name),
            style = MaterialTheme.typography.h5.copy(
                fontWeight = FontWeight.ExtraBold
            ),
            modifier = Modifier
                .padding(
                    top = 32.dp
                )
        )
        Text(
            text = stringResource(R.string.my_email),
            style = MaterialTheme.typography.body2
        )
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun AboutScreenPreview() {
    JetpackComposeMoviesCatalogueTheme {
        AboutScreen()
    }
}
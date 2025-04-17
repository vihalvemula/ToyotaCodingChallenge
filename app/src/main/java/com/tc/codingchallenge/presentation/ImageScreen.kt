package com.tc.codingchallenge.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun ImageScreen(modifier: Modifier = Modifier) {

    Box(modifier = modifier) {
        AsyncImage(
            model="https://cdn.britannica.com/52/4652-050-9321DACE/Texas-political-map-county-boundaries-cities.jpg",
            contentDescription = "Image"
        )
    }

}
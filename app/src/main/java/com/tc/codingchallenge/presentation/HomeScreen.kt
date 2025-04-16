package com.tc.codingchallenge.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(
    modifier: Modifier,
    onQuotes: () -> Unit,
    onSearch: () -> Unit,
    onImage: () -> Unit,
) {
    Column (modifier = modifier ){
        Button(
            onClick = onQuotes
        ) {
            Text("All Quotes")
        }
        Button(
            onClick = onSearch
        ) {
            Text("Search")
        }
        Button(
            onClick = onImage
        ) {
            Text("Image")
        }
    }

}
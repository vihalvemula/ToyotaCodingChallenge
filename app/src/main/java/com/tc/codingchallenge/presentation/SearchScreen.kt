package com.tc.codingchallenge.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: QuotesViewModel
) {
    var query by remember { mutableStateOf("") }
    var searching by remember{ mutableStateOf(false) }
    Column(modifier = modifier) {
        OutlinedTextField(
            value = query,
            onValueChange = {query = it}
        )
        Button(
            onClick = {
                viewModel.searchQuotes(query)
                searching = true
            }
        ) {
            Text("Search")
        }
        if(searching){
            QuotesListScreen(viewModel = viewModel)
        }
    }
}
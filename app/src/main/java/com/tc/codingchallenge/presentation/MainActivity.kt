package com.tc.codingchallenge.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tc.codingchallenge.ui.theme.CodingChallengeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel:QuotesViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navHost = rememberNavController()
            CodingChallengeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                NavigationGraph(modifier = Modifier.padding(innerPadding),navHost,viewModel)
                //QuotesListScreen(modifier = Modifier.padding(innerPadding),viewModel)
                }
            }
        }
    }
}

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: QuotesViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "HOME"
    ) {
        composable("HOME"){
            HomeScreen(
                modifier = modifier,
                onQuotes = {
                    navController.navigate("QUOTES")
                },
                onSearch = {
                    navController.navigate("SEARCH")
                },
                onImage = {
                    navController.navigate("IMAGE")
                }
            )
        }
        composable("QUOTES"){
            viewModel.getQuotes()
            QuotesListScreen(
                modifier = modifier,
                viewModel = viewModel
            )
        }
        composable("SEARCH"){
            SearchScreen(
                modifier = modifier,
                viewModel = viewModel
            )
        }
        composable("IMAGE"){
            ImageScreen(modifier = modifier)
        }
    }
}

@Composable
fun QuotesListScreen(modifier: Modifier=Modifier,viewModel: QuotesViewModel){
    val quotes by viewModel.quotes.collectAsState()

    when(val state = quotes){
        is Uistate.LOADING->{
            Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                CircularProgressIndicator()
            }
        }
        is Uistate.SUCCESS ->{
            LazyColumn(
                modifier
                    .fillMaxWidth()
                    .padding(8.dp)) {
                items(state.data){item->
                    Text(text = "${item.id}")
                    Text(text = "${item.quote}")
                    Text(text = "${item.author}")
                }
            }
        }
        is Uistate.ERROR->{
            Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                Text(text = "${state.error}")
            }
        }
    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CodingChallengeTheme {
        Greeting("Android")
    }
}
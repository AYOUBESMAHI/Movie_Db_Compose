package com.example.moviesdb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.moviesdb.Repositories.MoviesRepository
import com.example.moviesdb.models.Movie
import com.example.moviesdb.screens.CategoryScreen
import com.example.moviesdb.screens.DetailsScreen
import com.example.moviesdb.screens.HomeScreen
import com.example.moviesdb.screens.Screen
import com.example.moviesdb.ui.theme.MoviesDbTheme
import com.example.moviesdb.ui.theme.primary

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            MoviesDbTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = primary
                ) {
                    Navigation()
                }
            }
        }
    }

    @Composable
    fun Navigation() {
        val mainViewModel: MainViewModel = viewModel()
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
            composable(Screen.HomeScreen.route) {
                HomeScreen(navController, viewModel = mainViewModel)
            }
            composable(
                Screen.CategoriesScreen.route + "/{category}",
                arguments = listOf(navArgument("category") {
                    type = NavType.StringType
                    nullable = false
                })
            ) { entry ->
                val category = entry.arguments!!.getString("category").toString()
                CategoryScreen(navController, mainViewModel, category)
            }
            composable(
                Screen.DetailsScreen.route + "/{category}/{id}",
                arguments = listOf(
                    navArgument("category") {
                        type = NavType.StringType
                        nullable = false
                    },
                    navArgument("id") {
                        type = NavType.LongType
                        nullable = false
                    })
            ) {
                val id = it.arguments?.getLong("id")
                val category = it.arguments?.getString("category")
                val movie=navController.previousBackStackEntry?.savedStateHandle?.get<Movie>("movie")
                if (id != null && !category.isNullOrEmpty()) {
                    if(movie!=null){
                        DetailsScreen(navController, mainViewModel, category, movie, id)
                    }
                    else {
                        DetailsScreen(navController, mainViewModel, category, movie = null, id)
                    }
                }
            }

        }
    }
}


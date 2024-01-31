package com.example.moviesdb.composables

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.moviesdb.MainViewModel
import com.example.moviesdb.R
import com.example.moviesdb.screens.Screen
import com.example.moviesdb.ui.theme.secondary
import com.example.moviesdb.utils.Constants
import java.util.UUID

@Composable
fun TopBarTrends(
    navController: NavHostController,
    viewModel: MainViewModel) {


    val movies = viewModel.categoriesMovies.value["Now Playing"]!!
    val scrollState = rememberLazyListState(initialFirstVisibleItemIndex = 2)

    if (movies.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = secondary)

        }
   } else {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp),
            state = scrollState
        ) {
            items(7, key = {UUID.randomUUID()}) {
                val movie = movies[it]
                AsyncImage(
                    model = "${Constants.Image_Url}${movie.posterPath}",
                    contentDescription = null,
                    contentScale = ContentScale.Crop,

                    modifier = Modifier
                        .height(350.dp)
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .clickable { navController.navigate(Screen.DetailsScreen.route + "/Now Playing/${movie.id}") }
                )
            }
        }
    }
}

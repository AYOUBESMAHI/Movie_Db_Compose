package com.example.moviesdb.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.moviesdb.MainViewModel
import com.example.moviesdb.R
import com.example.moviesdb.models.Movie
import com.example.moviesdb.screens.Screen
import com.example.moviesdb.utils.Constants


@Composable
fun ItemRecommendations(movie: Movie,
                        navController: NavHostController,
                        mainViewModel: MainViewModel) {
    Column(Modifier.padding(8.dp)) {
        Text(
            text = "Recommendations", style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                color = Color.White
            )
        )
        Divider()
        Spacer(modifier = Modifier.height(12.dp))

        LazyRow {

            items(movie.recommendations) {

                Column(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .clickable {
                            mainViewModel.recommendation.value=null
                            navController.currentBackStackEntry?.savedStateHandle?.set("movie",it)
                            navController.navigate(Screen.DetailsScreen.route + "/similar/${it.id}")
                        },
                    verticalArrangement = Arrangement.Center
                ) {
                    AsyncImage(
                        model = "${Constants.Image_Url}${it.posterPath}",
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(190.dp)
                            .padding(horizontal = 6.dp)
                            .border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp))
                            .clip(shape = RoundedCornerShape(8.dp))
                    )
                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = it.title,
                        modifier = Modifier.width(145.dp),
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                }

            }
        }
    }
}

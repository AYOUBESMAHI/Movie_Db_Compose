package com.example.moviesdb.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.moviesdb.R
import com.example.moviesdb.models.Movie
import com.example.moviesdb.screens.Screen
import com.example.moviesdb.ui.theme.secondary
import com.example.moviesdb.utils.Constants
import java.util.Locale.Category

@Composable
fun HomeCategories(
    navController: NavHostController,
    category: String,
    movies: List<Movie>
) {
    if (movies.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = secondary)

        }
    } else {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(2.dp)
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 2.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = category,
                    style = TextStyle(
                        color = secondary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                )
                Text(
                    text = "See All",
                    style = TextStyle(
                        color = Color.Gray,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    ),
                    modifier = Modifier.clickable {
                        navController.navigate(Screen.CategoriesScreen.route + "/${category}")
                    }
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            LazyRow {
                items(movies, key = { it.id }) {
                    Column(
                        Modifier
                            .padding(horizontal = 3.dp)
                            .clickable {
                                navController.navigate(Screen.DetailsScreen.route+"/${category}/${it.id}")
                            },
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        SingleHomeMovieItem(it)
                    }
                }
            }
            Divider(color = Color.White)
            Spacer(modifier = Modifier.height(6.dp))
        }
    }
}


@Composable
fun SingleHomeMovieItem(movie: Movie) {
    AsyncImage(
        model = "${Constants.Image_Url}${movie.posterPath}",
        contentDescription = null,
        contentScale = ContentScale.Crop, modifier = Modifier
            .height(200.dp)
            .clip(RoundedCornerShape(4))
    )

    Spacer(modifier = Modifier.height(4.dp))

    Text(
        text = movie.title,
        modifier = Modifier.width(145.dp),
        color = Color.White,
        textAlign = TextAlign.Center,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
    Spacer(modifier = Modifier.height(4.dp))
}

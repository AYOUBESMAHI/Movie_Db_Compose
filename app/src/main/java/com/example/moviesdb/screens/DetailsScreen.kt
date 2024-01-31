package com.example.moviesdb.screens

import android.provider.MediaStore.Images
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.moviesdb.MainViewModel
import com.example.moviesdb.R
import com.example.moviesdb.composables.ItemActors
import com.example.moviesdb.composables.ItemDetailsInfo
import com.example.moviesdb.composables.ItemOverview
import com.example.moviesdb.composables.ItemPosters
import com.example.moviesdb.composables.ItemRecommendations
import com.example.moviesdb.composables.movie
import com.example.moviesdb.models.Movie
import com.example.moviesdb.ui.theme.secondary
import com.example.moviesdb.utils.Constants
import com.gowtham.ratingbar.RatingBar

@Composable
fun DetailsScreen(
    navController: NavHostController,
    mainViewModel: MainViewModel,
    category: String,
    movie: Movie?,
    movieId: Long
) {

    val detailMovie = if (category == "similar") {
        mainViewModel.recommendation.value=movie
        movie
    } else {
        mainViewModel.categoriesMovies.value[category]?.find { it.id == movieId }
    }

    if (detailMovie != null) {
        if (detailMovie.actors.isEmpty()) {
            mainViewModel.FeatchMovieActors(category, movieId)
        }
        if (detailMovie.images.isEmpty()) {
            mainViewModel.FeatchMovieImages(category, movieId)
        }
        if (detailMovie.recommendations.isEmpty()) {
            mainViewModel.FeatchSimilarMovies(category, movieId)
        }


        LazyColumn {
            item {
                val path = if (detailMovie.poster.isEmpty()) {
                    detailMovie.posterPath
                } else {
                    detailMovie.poster
                }
                AsyncImage(
                    model = "${Constants.Poster_Image_Url}${path}",
                    contentDescription = null,
                    modifier = Modifier
                        .height(600.dp)
                )

            }
            item {
                ItemDetailsInfo(detailMovie) { }
            }

            item {
                ItemOverview(detailMovie)
            }

            item {
                ItemActors(detailMovie)

            }
            item {
                ItemPosters(detailMovie)

            }

            item {
                ItemRecommendations(detailMovie, navController,mainViewModel)
            }


        }
    }
}



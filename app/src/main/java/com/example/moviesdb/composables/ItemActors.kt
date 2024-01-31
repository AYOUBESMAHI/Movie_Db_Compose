package com.example.moviesdb.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.core.content.res.FontResourcesParserCompat.FetchStrategy
import coil.compose.AsyncImage
import com.example.moviesdb.MainViewModel
import com.example.moviesdb.R
import com.example.moviesdb.models.Movie
import com.example.moviesdb.ui.theme.secondary
import com.example.moviesdb.utils.Constants

var movie: Movie? = null

@Composable
fun ItemActors(
    movie: Movie
) {


    Column(Modifier.padding(8.dp)) {
        Text(
            text = "Actors ", style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                color = Color.White
            )
        )
        Divider()
        Spacer(modifier = Modifier.height(12.dp))
        LazyRow {
            items(movie.actors, key = { it.id }) {
                Column(
                    Modifier.padding(horizontal = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (!it.profilePath.isNullOrEmpty()) {
                        AsyncImage(
                            model = "${Constants.Image_Url}${it.profilePath}",
                            contentDescription = null,
                            modifier = Modifier
                                .height(170.dp)
                                .clip(CircleShape)
                                .border(3.dp, secondary, shape = CircleShape)
                        )
                    } else {
                        Image(
                            painter = painterResource(id = R.drawable.user),
                            contentDescription = null,
                            modifier = Modifier
                                .height(170.dp)
                                .width(115.dp)
                                .clip(CircleShape)
                                .border(3.dp, secondary, shape = CircleShape),
                            contentScale = ContentScale.Crop
                        )
                    }

                    Text(text = it.name, color = Color.White, textAlign = TextAlign.Center)
                    Text(
                        text = "Shaun / Timmy (voice)",
                        modifier = Modifier.width(130.dp),
                        overflow = TextOverflow.Ellipsis,
                        color = Color.Gray,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}
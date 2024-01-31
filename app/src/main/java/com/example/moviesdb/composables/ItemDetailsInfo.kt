package com.example.moviesdb.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.moviesdb.R
import com.example.moviesdb.models.Movie
import com.example.moviesdb.ui.theme.secondary
import com.example.moviesdb.utils.Constants
import com.gowtham.ratingbar.RatingBar


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ItemDetailsInfo(movie: Movie, onClick: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(
                start = 6.dp,
                top = 6.dp,
                end = 6.dp,
                bottom = 12.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    )
    {
        AsyncImage(
            model = "${Constants.Image_Url}${movie.posterPath}",
            contentDescription = null,
            modifier = Modifier
                .height(220.dp)
                .clip(RoundedCornerShape(8))
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column(
            Modifier.padding(6.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = movie.title,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    color = Color.White
                )
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = movie.releaseDate,
                style = TextStyle(
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            )
            Spacer(modifier = Modifier.height(10.dp))

            RatingBar(
                painterEmpty = painterResource(id = R.drawable.empty),
                painterFilled = painterResource(id = R.drawable.star),
                numOfStars = 5,
                value = movie.voteAverage.toFloat() / 2,
                onValueChange = {},
                onRatingChanged = {})
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Genre : ",
                    style = TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                )

                FlowRow {
                    for (cat in movie.genreIds) {
                        val category =
                            Constants.genres.find { cat.toInt() == it.id }?.name.toString()
                        Text(
                            text = "$category,",
                            style = TextStyle(
                                color = Color.Gray,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            ),
                        )
                    }
                }

            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = onClick,
                elevation = ButtonDefaults.elevatedButtonElevation(180.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFF585858))
            ) {
                Row {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        tint = secondary,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "Favorite", style = TextStyle(
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }

    }
}







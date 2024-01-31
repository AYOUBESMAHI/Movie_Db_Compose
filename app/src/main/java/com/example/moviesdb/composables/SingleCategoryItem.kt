package com.example.moviesdb.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.moviesdb.R
import com.example.moviesdb.models.Movie
import com.example.moviesdb.utils.Constants
import com.gowtham.ratingbar.RatingBar


@Composable
fun SingleCategoryItem(movie: Movie, onClick: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(
                start = 6.dp,
                top = 6.dp,
                end = 6.dp,
                bottom = 12.dp
            )
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    )
    {
        AsyncImage(
            model = "${Constants.Image_Url}${movie.posterPath}",
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(200.dp)
                .width(140.dp)
                .clip(RoundedCornerShape(8))
        )
        Spacer(modifier = Modifier.width(8.dp))

        Column(Modifier.padding(6.dp)) {
            Text(
                text = movie.title,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
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
        }

    }
}

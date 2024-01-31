package com.example.moviesdb.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.moviesdb.R
import com.example.moviesdb.models.Movie
import com.example.moviesdb.utils.Constants


@Composable
fun ItemPosters(movie: Movie) {

    Column(Modifier.padding(8.dp)) {
        Text(
            text = "Posters", style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                color = Color.White
            )
        )
        Divider()
        Spacer(modifier = Modifier.height(12.dp))

        LazyRow {
            items(movie.images) {
                AsyncImage(
                    model = "${Constants.Image_Url}${it}",
                    contentDescription = null,
                    modifier = Modifier
                        .height(150.dp)
                        .padding(horizontal = 6.dp)
                        .border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp))
                        .clip(shape = RoundedCornerShape(8.dp))
                        ,
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}
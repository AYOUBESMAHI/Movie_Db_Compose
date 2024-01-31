package com.example.moviesdb.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviesdb.models.Movie


@Composable
 fun ItemOverview(movie: Movie) {
    Column(Modifier.padding(8.dp)) {
        Text(
            text = "Overview", style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                color = Color.White
            )
        )
        Divider()
        Text(
            text = movie.overview,
            style = TextStyle(
                fontSize = 17.sp,
                color = Color.White
            ),
            modifier = Modifier.padding(vertical = 5.dp)
        )
    }
}

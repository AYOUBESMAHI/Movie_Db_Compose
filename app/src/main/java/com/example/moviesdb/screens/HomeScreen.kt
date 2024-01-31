package com.example.moviesdb.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.moviesdb.MainViewModel
import com.example.moviesdb.R
import com.example.moviesdb.composables.HomeCategories
import com.example.moviesdb.composables.TopBarTrends
import com.example.moviesdb.ui.theme.secondary
import me.onebone.toolbar.CollapsingToolbar
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState
import me.onebone.toolbar.rememberCollapsingToolbarState
import java.util.UUID


@Composable
fun HomeScreen(navHostController: NavHostController,viewModel: MainViewModel) {

    for (cat in viewModel.categoriesMovies.value) {
        if (cat.value.isEmpty()) {
                viewModel.FeatchMoviesByGenre(cat.key)

        }
    }

    LazyColumn(Modifier.fillMaxSize()) {
        item { TopBarTrends(navHostController,viewModel) }
        item {
            Spacer(modifier = Modifier.height(8.dp))
        }
        for (cat in viewModel.categoriesMovies.value) {
            item {
                HomeCategories(navController = navHostController,cat.key, cat.value)
            }
        }
    }

}



package com.example.moviesdb

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesdb.Repositories.MoviesRepository
import com.example.moviesdb.models.Movie
import com.example.moviesdb.utils.Constants
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainViewModel() : ViewModel() {
    val categoriesMovies = mutableStateOf(HashMap<String, List<Movie>>())
    val recommendation = mutableStateOf<Movie?>(null)

    init {
        for (cat in Constants.genres) {
            categoriesMovies.value[cat.name] = emptyList()
        }
    }



    fun FeatchMoviesByGenre(genre: String) {
        viewModelScope.launch {
            val genreId = Constants.genres.find { it.name == genre }!!.id
            val movies = MoviesRepository().GetMoviesByGenre(genreId)
            val newMap = HashMap(categoriesMovies.value)
            newMap[genre] = movies
            categoriesMovies.value = newMap
        }
    }



    fun FeatchMovieActors(cat: String, id: Long) {
        viewModelScope.launch {
            val actors = MoviesRepository().GetMovieActors(id)
            if (cat == "similar") {
                recommendation.value?.actors = actors
            } else {
                val updatedCatMovies = categoriesMovies.value
                updatedCatMovies[cat]?.forEach {
                    if (it.id == id) {
                        it.actors = actors
                    }
                }
                categoriesMovies.value = updatedCatMovies
            }
        }
    }


    fun FeatchMovieImages(cat: String, id: Long) {
        viewModelScope.launch {
            val images = MoviesRepository().GetMovieImages(id)
            if (cat == "similar") {
                recommendation.value?.images = images.backdrops.map { it.filePath }
                if (images.posters.isNotEmpty()) {
                    if (images.posters.size > 2) {
                        Log.d("===>rslt","${images.posters.size}")
                        recommendation.value?.poster =
                            images.posters[Random.nextInt(
                                0,
                                images.posters.size - 1
                            )].filePath
                    } else {
                        recommendation.value?.poster = images.posters[0].filePath

                    }
                }

            } else {
                val updatedCatMovies = categoriesMovies.value
                updatedCatMovies[cat]?.forEach {
                    if (it.id == id) {
                        it.images = images.backdrops.map { it.filePath }
                        if (images.posters.isNotEmpty()) {
                            if (images.posters.size > 2) {
                                Log.d("===>rslt","${images.posters.size}")
                                it.poster =
                                    images.posters[Random.nextInt(
                                        0,
                                        images.posters.size - 1
                                    )].filePath
                            } else {
                                it.poster = images.posters[0].filePath

                            }
                        }

                    }
                }
                categoriesMovies.value = updatedCatMovies
            }
        }
    }


    fun FeatchSimilarMovies(cat: String, id: Long) {
        viewModelScope.launch {
            val movies = MoviesRepository().GetSimilarMovies(id)
            if (cat == "similar") {
                recommendation.value?.recommendations = movies

            } else {
                val updatedCatMovies = categoriesMovies.value
                updatedCatMovies[cat]?.forEach {
                    if (it.id == id) {
                        it.recommendations = movies
                    }
                }
                categoriesMovies.value = updatedCatMovies
            }
        }
    }

}




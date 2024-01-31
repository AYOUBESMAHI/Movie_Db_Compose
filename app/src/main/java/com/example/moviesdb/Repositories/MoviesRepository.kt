package com.example.moviesdb.Repositories

import android.util.Log
import com.example.moviesdb.models.Actor
import com.example.moviesdb.models.Images
import com.example.moviesdb.models.Movie
import com.example.moviesdb.services.RetrofitInstance
import com.google.gson.Gson
import com.google.gson.JsonElement

class MoviesRepository {
    suspend fun GetNowPlayingMovies(): List<Movie> {
        var movies: List<Movie> = emptyList()
        try {
            val response = RetrofitInstance().simpleApi.GetNowPlayingMovies()
            val moviesJson = response.asJsonObject.getAsJsonArray("results")
            movies = Gson().fromJson(moviesJson, Array<Movie>::class.java).toList()
        } catch (e: Exception) {
            Log.d("===>err GetNowPlayingMovies:", "${e.message}")
        }
        return movies
    }


    suspend fun GetMoviesByGenre(genre: Int): List<Movie> {
        var movies = emptyList<Movie>()
        try {
            val response =
                when (genre) {
                    -1 -> {
                        RetrofitInstance().simpleApi.GetNowPlayingMovies()
                    }

                    -2 -> {
                        RetrofitInstance().simpleApi.GetPopularMovies()
                    }

                    -3 -> {
                        RetrofitInstance().simpleApi.GetTopRatedMovies()
                    }

                    -4 -> {
                        RetrofitInstance().simpleApi.GetUpcomingMovies()
                    }

                    else -> {
                        RetrofitInstance().simpleApi.GetMoviesByGenre(genre)
                    }
                }
            val moviesJson = response.asJsonObject.getAsJsonArray("results")
            movies = Gson().fromJson(moviesJson, Array<Movie>::class.java).toList()

        } catch (e: Exception) {
            Log.d("===>err GetMoviesByGenre: ", "${e.message}")
        }
        return movies
    }


    suspend fun GetMovieActors(id: Long): List<Actor> {
        var actors = emptyList<Actor>()
        try {
            val response = RetrofitInstance().simpleApi.GetMovieActors(id)
            val responseJson = response.asJsonObject.getAsJsonArray("cast")
            actors = Gson().fromJson(responseJson, Array<Actor>::class.java).toList()

        } catch (e: Exception) {
            Log.d("===>err GetMovieActors:", "${e.message}")
        }
        return actors
    }

    suspend fun GetMovieImages(id: Long): Images {
        var images: Images = Images(emptyList(), emptyList())
        try {
            val response = RetrofitInstance().simpleApi.GetMovieImages(id)
            images = response

        } catch (e: Exception) {
            Log.d("===>err GetMovieImages:", "${e.message}")
        }
        return images
    }

    suspend fun GetSimilarMovies(id: Long): List<Movie> {
        var movies: List<Movie> = emptyList()
        try {
            val response = RetrofitInstance().simpleApi.GetSimilarMovies(id)
            val responseJson = response.asJsonObject.getAsJsonArray("results")
            movies = Gson().fromJson(responseJson, Array<Movie>::class.java).toList()

        } catch (e: Exception) {
            Log.d("===>err GetMovieImages:", "${e.message}")
        }
        return movies
    }

    suspend fun GetMovieDetails(id: Long): Movie {
        var movie: Movie = Movie()
        try {
            val response = RetrofitInstance().simpleApi.GetMovieDetails(id)
            movie = response

        } catch (e: Exception) {
            Log.d("===>err GetMovieDetails:", "${e.message}")
        }
        return movie
    }


}
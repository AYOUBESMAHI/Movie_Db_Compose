package com.example.moviesdb.utils

import com.example.moviesdb.models.Genre

class Constants {
    companion object {
        const val Main_Url = "https://api.themoviedb.org/3/"
        const val Image_Url = "https://image.tmdb.org/t/p/w300"
        const val Poster_Image_Url = "https://image.tmdb.org/t/p/w500"



        val genres: List<Genre> = listOf(
            Genre(id = -1, name = "Now Playing"),
            Genre(id = -2, name = "Popular"),
            Genre(id = -3, name = "Top Rated"),
            Genre(id = -4, name = "Upcoming"),
            Genre(id = 28, name = "Action"),
            Genre(id = 28, name = "Action"),
            Genre(id = 12, name = "Adventure"),
            Genre(id = 16, name = "Animation"),
            Genre(id = 35, name = "Comedy"),
            Genre(id = 80, name = "Crime"),
            Genre(id = 10751, name = "Family"),
            Genre(id = 14, name = "Fantasy"),
            Genre(id = 36, name = "History"),
            Genre(id = 27, name = "Horror"),
            Genre(id = 10402, name = "Music"),
            Genre(id = 9648, name = "Mystery"),
            Genre(id = 878, name = "Science Fiction"),
            Genre(id = 10770, name = "TV Movie"),
            Genre(id = 53, name = "Thriller"),
            Genre(id = 10752, name = "War"),
            Genre(id = 37, name = "Western")

        )


    }
}
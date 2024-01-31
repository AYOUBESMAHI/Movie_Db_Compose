package com.example.moviesdb.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    @SerializedName("backdrop_path")
    var backdropPath: String = "",
    @SerializedName("genre_ids")
    var genreIds: List<Long> = emptyList(),
    var id: Long = 0L,
    @SerializedName("original_language")
    var originalLanguage: String = "",
    @SerializedName("original_title")
    var originalTitle: String = "",
    var overview: String = "",
    @SerializedName("poster_path")
    var posterPath: String = "",
    @SerializedName("release_date")
    var releaseDate: String = "",
    var title: String = "",
    @SerializedName("vote_average")
    var voteAverage: Double = 0.0,

    var actors: List<Actor> = emptyList(),
    var images: List<String> = emptyList(),
    var poster: String = "",
    var recommendations: List<Movie> = emptyList()

) : Parcelable


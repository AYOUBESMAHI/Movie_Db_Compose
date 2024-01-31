package com.example.moviesdb.services

import com.example.moviesdb.models.Actor
import com.example.moviesdb.models.Images
import com.example.moviesdb.models.Movie
import com.example.moviesdb.utils.Constants
import com.google.gson.JsonElement
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SimpleApi {
    @GET("movie/now_playing")
    suspend fun GetNowPlayingMovies(): JsonElement
    @GET("movie/popular")
    suspend fun GetPopularMovies(): JsonElement
    @GET("movie/top_rated")
    suspend fun GetTopRatedMovies(): JsonElement
    @GET("movie/upcoming")
    suspend fun GetUpcomingMovies(): JsonElement

    @GET("discover/movie")
    suspend fun GetMoviesByGenre(
        @Query("with_genres") genre: Int,
        @Query("include_adult") include_adult: Boolean = false,
        @Query("language") language: String = "en-US",
        @Query("region") region: String = "US",
        @Query("sort_by") sort_by: String = "popularity.desc",
        @Query("without_genres") without_genres: String = "99,18",
    ): JsonElement

    @GET("movie/{movie_id}/credits")
    suspend fun GetMovieActors(
        @Path("movie_id") id: Long,
        @Query("language") language: String = "en-US"
    ): JsonElement


    @GET("movie/{movie_id}/images")
    suspend fun GetMovieImages(
        @Path("movie_id") id: Long,
        @Query("include_image_language") include_image_language: String = "en"
    ): Images



    @GET("movie/{movie_id}/similar")
    suspend fun GetSimilarMovies(
        @Path("movie_id") id: Long,
        @Query("language") include_image_language: String = "en-US"
    ): JsonElement


    @GET("movie/{movie_id}")
    suspend fun GetMovieDetails(
        @Path("movie_id") id: Long,
        @Query("language") include_image_language: String = "en-US"
    ): Movie

}
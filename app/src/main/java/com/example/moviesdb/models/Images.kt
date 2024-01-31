package com.example.moviesdb.models

import com.google.gson.annotations.SerializedName

data class Images(
    val backdrops: List<Backdrop>,
    val posters: List<Poster>,
)

data class Backdrop(

    @SerializedName("file_path")
    val filePath: String
)


data class Poster(
    @SerializedName("file_path")
    val filePath: String,
)

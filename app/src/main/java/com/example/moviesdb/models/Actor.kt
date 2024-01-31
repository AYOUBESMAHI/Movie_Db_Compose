package com.example.moviesdb.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Actor(
    val id: Long,
    val name: String,
    @SerializedName("profile_path")
    val profilePath: String?,
    val character: String,
):Parcelable

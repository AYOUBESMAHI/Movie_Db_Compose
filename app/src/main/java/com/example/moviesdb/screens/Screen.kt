package com.example.moviesdb.screens

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home")
    object CategoriesScreen : Screen("categories")
    object DetailsScreen : Screen("details")
}
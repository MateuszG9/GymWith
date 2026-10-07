package com.example.gymwith.ui.navigation

// Definicja tras ekranów

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object Dashboard : Screen("dashboard")
    object Workout : Screen("workout")
    object Calendar : Screen("calendar")
    object Community : Screen("community")
    object Profile : Screen("profile")
}
package me.fakhry.jetpackcomposemoviescatalogue.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")

    object Detail : Screen("home/{id}") {
        fun createRoute(id: Long) = "home/$id"
    }

    object About : Screen("about")
}
package org.robert.project.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.robert.project.ui.NoteDestinationsArgs.ANIME_ID_ARG
import org.robert.project.ui.detail.AnimeDetailView
import org.robert.project.ui.list.AnimeListView
import org.robert.project.utils.ConnectivityService

object NoteDestinationsArgs {
    const val ANIME_ID_ARG = "animeId"
}

@Composable
fun Navigation(networkMonitor: ConnectivityService) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.ListScreen.route) {
        composable(route = Screen.ListScreen.route) {
            AnimeListView(networkMonitor = networkMonitor, navController = navController)
        }
        composable(
            route = Screen.DetailScreen.route + "?id={$ANIME_ID_ARG}",
            arguments = listOf(
                navArgument(ANIME_ID_ARG) { type = NavType.IntType; defaultValue = -1 },
            )
        ) { entry ->
            AnimeDetailView(
                animeId = entry.arguments?.getInt(ANIME_ID_ARG),
                navController = navController
            )
        }
    }
}
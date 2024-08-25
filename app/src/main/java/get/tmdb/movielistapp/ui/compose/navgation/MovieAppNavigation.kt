package get.tmdb.movielistapp.ui.compose.navgation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import get.tmdb.movielistapp.ui.compose.screen.HomeListingScreen

@Composable
fun MovieAppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Destinations.HomeListingScreen.route) {

        composable(route = Destinations.HomeListingScreen.route) {
            HomeListingScreen { moviesListItem ->
//                TODO: Add Movie Details Screen routing here and pass the moviesListItem
            }
        }
    }
}
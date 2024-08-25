package get.tmdb.movielistapp.ui.compose.navgation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import get.tmdb.movielistapp.domain.MovieListItem
import get.tmdb.movielistapp.ui.compose.screen.HomeListingScreen
import get.tmdb.movielistapp.ui.compose.screen.MovieDetailsScreen
import get.tmdb.movielistapp.ui.theme.slideInHorizontally
import get.tmdb.movielistapp.ui.theme.slideOutHorizontally
import kotlin.reflect.typeOf

@Composable
fun MovieAppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = HomeListingRoute) {

        composable<HomeListingRoute> {
            HomeListingScreen { movieListItem ->
                navController.navigate(
                    MovieDetailRoute(movieListItem = movieListItem)
                )
            }
        }

        composable<MovieDetailRoute>(
            enterTransition = { slideInHorizontally },
            exitTransition = { slideOutHorizontally },
            typeMap = mapOf(
                typeOf<MovieListItem>() to CustomNavType.MovieItemType
            )
        ) {
            val arguments = it.toRoute<MovieDetailRoute>()
            MovieDetailsScreen(movieItem = arguments.movieListItem){
                navController.popBackStack()
            }
        }

    }
}
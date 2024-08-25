package get.tmdb.movielistapp.ui.compose.navgation

import get.tmdb.movielistapp.domain.MovieListItem
import kotlinx.serialization.Serializable

/**
 * Routes to be used in the navigation graph for each screen
 */

@Serializable
data object HomeListingRoute

@Serializable
data class MovieDetailRoute(
    val movieListItem: MovieListItem
)
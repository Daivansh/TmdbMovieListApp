package get.tmdb.movielistapp.ui.compose.navgation

import get.tmdb.movielistapp.domain.MovieListItem
import kotlinx.serialization.Serializable

@Serializable
data object HomeListingRoute

@Serializable
data class MovieDetailRoute(
    val movieListItem: MovieListItem
)
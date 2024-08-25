package get.tmdb.movielistapp.usecase.model

import get.tmdb.movielistapp.usecase.TrendingMoviesListUseCase
import get.tmdb.movielistapp.usecase.SearchMoviesListUseCase

/**
 * This data class is used to inject the use cases into the viewmodels
 */
data class Interactors(
    val trendingMoviesListUseCase: TrendingMoviesListUseCase,
    val searchMoviesUseCase: SearchMoviesListUseCase
)

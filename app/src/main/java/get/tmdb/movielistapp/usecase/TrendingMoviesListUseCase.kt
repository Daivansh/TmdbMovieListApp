package get.tmdb.movielistapp.usecase

import get.tmdb.movielistapp.api.model.ApiResult
import get.tmdb.movielistapp.api.repo.MovieRepository
import get.tmdb.movielistapp.domain.MovieListResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * MoviesListUseCase class to be used in ViewModel to get trending movie list from the api based on the page number
 */
class TrendingMoviesListUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    //get movie list
    suspend operator fun invoke(page: Int) : Flow<ApiResult<MovieListResponse?>> {
        return movieRepository.getTrendingMovieList(page)
    }
}
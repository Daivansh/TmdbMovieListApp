package get.tmdb.movielistapp.usecase

import get.tmdb.movielistapp.api.model.ApiResult
import get.tmdb.movielistapp.api.repo.MovieRepository
import get.tmdb.movielistapp.domain.MovieListResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * SearchMoviesListUseCase class to be used in ViewModel to get movie list based on search query
 */
class SearchMoviesListUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    //get movie list
    suspend operator fun invoke(page: Int, query: String) : Flow<ApiResult<MovieListResponse?>> {
        return movieRepository.searchMovieList(page, query)
    }
}
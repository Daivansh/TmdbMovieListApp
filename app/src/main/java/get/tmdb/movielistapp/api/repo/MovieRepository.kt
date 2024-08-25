package get.tmdb.movielistapp.api.repo

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import get.tmdb.movielistapp.api.MovieApiService
import get.tmdb.movielistapp.api.model.ApiResult
import get.tmdb.movielistapp.domain.MovieListResponse
import get.tmdb.movielistapp.utils.Constants
import get.tmdb.movielistapp.utils.NetworkUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * MovieRepository class is used to make actual retrofit api calls related to the movies
 * It converts the api response to the ApiResult object and emits it to the flow to be consumed by the ViewModel
 */
class MovieRepository @Inject constructor(@ApplicationContext private val context: Context, private val movieApiService: MovieApiService){

    /**
     * getTrendingMovieList method is used to get the list of trending movies
     * It takes the page number as an argument and returns a flow of ApiResult<MovieListResponse?>
     */
    suspend fun getTrendingMovieList(page: Int): Flow<ApiResult<MovieListResponse?>> = flow {
        if (!NetworkUtils.isNetworkAvailable(context)) {
            emit(ApiResult.Failure(true, Constants.NO_INTERNET, null))
            return@flow
        }

        emit(ApiResult.Loading)

        val response = movieApiService.getTrendingMovieList(page = page)
        runCatching {
            if (response.isSuccessful) {
                emit(ApiResult.Success(response.body()))
            } else {
                emit(ApiResult.Failure(false, response.message(), response.code()))
            }
        }.onFailure {
            emit(ApiResult.Failure(false, it.message, null))
        }

    }.flowOn(Dispatchers.IO)


    /**
     * searchMovieList method is used to search for movies list based on the query
     * It takes the page number and the query as arguments and returns a flow of ApiResult<MovieListResponse?>
     */
    suspend fun searchMovieList(
        page: Int,
        query: String
    ): Flow<ApiResult<MovieListResponse?>> = flow<ApiResult<MovieListResponse?>> {
        if (!NetworkUtils.isNetworkAvailable(context)) {
            emit(ApiResult.Failure(true, Constants.NO_INTERNET, null))
            return@flow
        }

        emit(ApiResult.Loading)

        val response = movieApiService.searchMovie(page, query)
        runCatching {
            if (response.isSuccessful) {
                emit(ApiResult.Success(response.body()))
            } else {
                emit(ApiResult.Failure(false, response.message(), response.code()))
            }
        }.onFailure {
            emit(ApiResult.Failure(false, it.message, null))
        }
    }.flowOn(Dispatchers.IO)
}
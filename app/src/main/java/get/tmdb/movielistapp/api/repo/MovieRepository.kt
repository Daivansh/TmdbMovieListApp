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

class MovieRepository @Inject constructor(@ApplicationContext private val context: Context, private val movieApiService: MovieApiService){

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

    suspend fun searchMovieList(
        page: Int,
        query: String
    ): Flow<ApiResult<MovieListResponse?>> = flow<ApiResult<MovieListResponse?>> {
        if (!NetworkUtils.isNetworkAvailable(context)) {
            emit(ApiResult.Failure(true, Constants.NO_INTERNET, null))
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
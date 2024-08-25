package get.tmdb.movielistapp.api

import get.tmdb.movielistapp.domain.MovieListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

//get trending movies list for current week
    @GET("trending/movie/week")
    suspend fun getTrendingMovieList(
        @Query("page") page: Int
    ): Response<MovieListResponse?>

//search movie by name
    @GET("search/movie")
    suspend fun searchMovie(
        @Query("page") page: Int,
        @Query("query") query: String?
    ): Response<MovieListResponse?>
}
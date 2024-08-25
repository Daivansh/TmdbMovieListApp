package get.tmdb.movielistapp.domain

import com.google.gson.annotations.SerializedName

/**
 * MovieListResponse class to be used in the api response to get the list of movies
 */
data class MovieListResponse(

    @SerializedName("page")
    val page: Int? = null,

    @SerializedName("results")
    val results: List<MovieListItem>? = null,

    @SerializedName("total_results")
    val totalResults: Int? = null,

    @SerializedName("total_pages")
    val totalPages: Int? = null
)
package get.tmdb.movielistapp.api.model

sealed class ApiResult<out T> {

//    Keeping Loading as object as I dont want to pass any param here, and data class must have a primary constructor with at least 1 param
    object Loading : ApiResult<Nothing>()

    data class Success<out T>(val value: T) : ApiResult<T>()

    data class Failure(
        val isInternetIssue: Boolean,
        val errorMessage: String?,
        val errorCode: Int?,
    ) : ApiResult<Nothing>()

}
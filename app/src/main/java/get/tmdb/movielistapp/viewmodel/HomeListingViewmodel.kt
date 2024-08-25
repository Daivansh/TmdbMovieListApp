package get.tmdb.movielistapp.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import get.tmdb.movielistapp.api.model.ApiResult
import get.tmdb.movielistapp.domain.MovieListItem
import get.tmdb.movielistapp.usecase.model.Interactors
import get.tmdb.movielistapp.utils.Constants
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeListingViewmodel @Inject constructor(val interactors: Interactors) : ViewModel() {

    private var _isLoading = mutableStateOf<Boolean>(false)
    val isLoading: State<Boolean> = _isLoading

    private var _mutableMoviesListItems = mutableStateListOf<MovieListItem>()
    val moviesListItems: List<MovieListItem> = _mutableMoviesListItems

    private val _apiError = mutableStateOf<String?>(null)
    val apiError: State<String?> = _apiError

    private val _isEmptyList = mutableStateOf(false)
    val isEmptyList: State<Boolean> = _isEmptyList

    init {
        loadTrendingMoviesListItems()
    }

    fun loadTrendingMoviesListItems() {
        viewModelScope.launch {
            interactors.trendingMoviesListUseCase.invoke(Constants.DEFAULT_LIST_PAGE_NO).collect {
                when (it) {

                    is ApiResult.Success -> {
                        _isLoading.value = false
                        _apiError.value = null
                        _mutableMoviesListItems.clear()

                        it.value?.results?.forEach { result ->
                            _mutableMoviesListItems.add(result)
                        }

                        _isEmptyList.value = _mutableMoviesListItems.size == 0
                    }

                    is ApiResult.Failure -> {
                        _apiError.value = it.errorMessage ?: Constants.ERROR_MSG_GENERIC
                        _isLoading.value = false
                    }

                    is ApiResult.Loading -> {
                        _isLoading.value = true
                        _apiError.value = null
                    }
                }

            }
        }

    }
}
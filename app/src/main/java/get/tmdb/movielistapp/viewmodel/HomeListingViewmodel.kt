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
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Home listing viewmodel which is used to load the list of movies at first time and also searched movies further based on the search query
 * It makes the use of use cases to make the api calls and converts their responses to the States
 * Such states are observed by the composable view in the UI which reacts to the data changes in such states
 *
 * Added a debounce search api job to avoid multiple api calls when the user types fast
 */
@HiltViewModel
class HomeListingViewmodel @Inject constructor(val interactors: Interactors) : ViewModel() {

    private var _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _searchText = mutableStateOf(Constants.STR_EMPTY)
    val searchText: State<String> = _searchText

    private var _mutableMoviesListItems = mutableStateListOf<MovieListItem>()
    val moviesListItems: List<MovieListItem> = _mutableMoviesListItems

    private val _apiError = mutableStateOf<String?>(null)
    val apiError: State<String?> = _apiError

    private val _isEmptyList = mutableStateOf(false)
    val isEmptyList: State<Boolean> = _isEmptyList

    private var debounceSearchApiJob: Job? = null

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

    fun onSearchFieldTextChange(text: String) {
        _searchText.value = text

        // Cancel the previous debounce job if it's still active, as we don't want to search for the same text anymore
        debounceSearchApiJob?.cancel()

        if(text.isBlank()){
            loadTrendingMoviesListItems()
            return
        }

        // Launch a new debounce job
        debounceSearchApiJob = viewModelScope.launch {
            // Wait for 500ms after the user stops typing
            delay(Constants.DEBOUNCE_TIME)
            searchFromMoviesListItems(text)
        }
    }

    private suspend fun searchFromMoviesListItems(query: String) {
        interactors.searchMoviesUseCase.invoke(Constants.DEFAULT_LIST_PAGE_NO, query).collect {
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
package get.tmdb.movielistapp.ui.compose.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import get.tmdb.movielistapp.R
import get.tmdb.movielistapp.domain.MovieListItem
import get.tmdb.movielistapp.ui.compose.views.MovieItemCard
import get.tmdb.movielistapp.ui.compose.views.SearchView
import get.tmdb.movielistapp.viewmodel.HomeListingViewmodel

/**
 * Home listing screen which is the main screen of the app
 * Its a first screen of the navigation graph and it is used to display the list of movies
 *
 * @param homeListingViewModel ViewModel to be used for the home listing screen to laod list of movies at first time and searched movies
 * @param navigateToMovieDetails callback to fire to navigate to the movie details screen
 */
@Composable
fun HomeListingScreen(
    homeListingViewModel: HomeListingViewmodel = hiltViewModel(),
    navigateToMovieDetails: (MovieListItem) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.padding_medium))
    ) {
        SearchUI(homeListingViewModel.searchText.value){
            homeListingViewModel.onSearchFieldTextChange(it)
        }
        MoviesList(homeListingViewModel.moviesListItems, navigateToMovieDetails)
        LoadingScreen(homeListingViewModel.isLoading.value)
        EmptyScreen(homeListingViewModel.isEmptyList.value)
        ErrorScreen(homeListingViewModel.apiError.value){
//            retry api call with the search text query if present else load the list of trending movies
            if(homeListingViewModel.searchText.value.isEmpty()){
                homeListingViewModel.loadTrendingMoviesListItems()
            }else{
                homeListingViewModel.onSearchFieldTextChange(text = homeListingViewModel.searchText.value)
            }
        }
    }
}


/**
 * Search UI which is used in the home listing screen for searching movies
 *
 * @param searchText String for the search text to be displayed in the view
 * @param onTextChange Callback to be fired when the text is changed in the view
 */
@Composable
fun SearchUI(searchText: String, onTextChange: (String) -> Unit) {
    SearchView(
        searchText = searchText,
        placeHolder = stringResource(id = R.string.search_bar_hint),
        onTextChange = { onTextChange(it) },
    )
}


/**
 * Movies list which is used in the home listing screen for displaying movies items
 *
 * @param moviesList List of MovieListItem objects which contains the details of the movies to be displayed
 * @param navigateToMovieDetails callback to fire to navigate to the movie details screen from the card
 */
@Composable
fun MoviesList(moviesList: List<MovieListItem>, navigateToMovieDetails: (MovieListItem) -> Unit) {
    if(moviesList.isEmpty()){
        return
    }

    LazyVerticalGrid(columns = GridCells.Fixed(2)
        , modifier = Modifier.fillMaxSize()
        , verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.margin_between_cards))
        , horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.margin_between_cards))) {
        items(moviesList.size) { i ->
            MovieItemCard(
                item = moviesList[i],
                modifier = Modifier.fillMaxWidth(),
                navigateToMovieDetails
            )
        }
    }
}


/**
 * Loading screen which is used in the home listing screen while the api to fetch the list of movies is ongoing
 *
 * @param isLoading Boolean to check if the api to fetch the list of movies is ongoing
 */
@Composable
fun LoadingScreen(isLoading: Boolean) {
    if(isLoading){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                modifier = Modifier.width(dimensionResource(id = R.dimen.progress_indicator_width)),
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant
            )
        }
    }
}


/**
 * Error screen to show in the home listing screen when the api to fetch the list of movies fails
 * It contains the error text and a retry button to retry the api call again in case of failure
 *
 * @param errorText String to be displayed in the error screen
 * @param onRetryClick Callback to be fired when the retry button is clicked
 */
@Composable
fun ErrorScreen(errorText: String?, onRetryClick: () -> Unit) {
    if(errorText.isNullOrEmpty()){
        return
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = errorText, style = MaterialTheme.typography.labelMedium)
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_small)))
        Button(onClick = onRetryClick) {
            Text(text = stringResource(id = R.string.error_btn_retry))
        }
    }
}


/**
 * Empty screen to show in the home listing screen when there is no movies to be displayed for the search query
 *
 * @param isListEmpty Boolean to check if the list of movies is empty
 */
@Composable
fun EmptyScreen(isListEmpty: Boolean) {
    if(isListEmpty) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(id = R.string.empty_list_msg) , style = MaterialTheme.typography.labelMedium)
        }
    }
}

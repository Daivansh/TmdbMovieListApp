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
            homeListingViewModel.loadTrendingMoviesListItems()
        }
    }
}

@Composable
fun SearchUI(searchText: String, onTextChange: (String) -> Unit) {
    SearchView(
        searchText = searchText,
        placeHolder = stringResource(id = R.string.search_bar_hint),
        onTextChange = { onTextChange(it) },
    )
}

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

package get.tmdb.movielistapp.ui.compose.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import get.tmdb.movielistapp.R
import get.tmdb.movielistapp.domain.MovieListItem
import get.tmdb.movielistapp.ui.theme.Grey600
import get.tmdb.movielistapp.utils.Constants

/**
 * Movie details screen which is used to display the details of a movie
 * Its a second screen of the navigation graph and it is used to display the details of a movie
 *
 * @param movieItem MovieListItem object which contains the details of the movie to be displayed here
 * @param onBackClick callback to fire to navigate back to the home listing screen, it will be passed to the top app bar for the back button
 */
@Composable
fun MovieDetailsScreen(
    movieItem: MovieListItem,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = { TopAppBar(onBackClick) }
    ) { _ ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = dimensionResource(id = R.dimen.padding_medium))
                .padding(top = dimensionResource(id = R.dimen.toolbar_height) , bottom = dimensionResource(id = R.dimen.padding_medium))
        ) {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(Constants.LARGE_IMAGE_URL + movieItem.posterPath)
                    .crossfade(true)
                    .build(),
                contentDescription = Constants.CONTENT_DESC_LARGE_SIZE_IMAGE,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.image_height_in_detail_page))
                    .padding(bottom = dimensionResource(id = R.dimen.padding_medium))
                    .clip(RoundedCornerShape(dimensionResource(id = R.dimen.corner_radius_medium))),
                loading = {
                    Column(modifier = Modifier.size(dimensionResource(id = R.dimen.progress_indicator_width)),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        CircularProgressIndicator()
                    }
                },
                error = {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = stringResource(id = R.string.error_image_loading_txt), color = MaterialTheme.colorScheme.error)
                    }
                }
            )

            Text(
                text = movieItem.title.orEmpty(),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.padding_medium))
            )

            Text(
                text = movieItem.overview.orEmpty(),
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

/**
 * Top app bar which is used in the movie details screen as the top bar
 * It contains the back button to navigate back to the home listing screen
 *
 * @param onBackClick callback to fire to navigate back to the home listing screen
 */
@Composable
fun TopAppBar(onBackClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.toolbar_height))
    ) {
        IconButton(onClick = onBackClick) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = Constants.CONTENT_DESC_BACK_BTN,
                tint = Grey600
            )
        }
    }
}

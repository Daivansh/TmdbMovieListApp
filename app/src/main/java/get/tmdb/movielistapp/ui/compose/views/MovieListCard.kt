package get.tmdb.movielistapp.ui.compose.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import get.tmdb.movielistapp.R
import get.tmdb.movielistapp.domain.MovieListItem
import get.tmdb.movielistapp.utils.Constants


/**
 * Movie item card which is being added for each movie in the home listing screen
 *
 * @param item MovieListItem object which contains the details of the movie to be displayed in each card
 * @param modifier Modifier for the card layout properties
 * @param navigateToMovieDetails callback to fire to navigate to the movie details screen from the card
 */
@Composable
fun MovieItemCard(item: MovieListItem, modifier: Modifier, navigateToMovieDetails: (movieListItem: MovieListItem) -> Unit) {
    Box(modifier = Modifier
        .background(color = Color.White)
        .clickable {
            navigateToMovieDetails(item)
        }) {
        Column(
            modifier = modifier
        ) {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(Constants.SMALL_IMAGE_URL + item.posterPath)
                    .crossfade(true)
                    .build(),
                contentDescription = Constants.CONTENT_DESC_SMALL_SIZE_IMAGE,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.image_height_in_card))
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
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = item.title ?: "",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold
            )
        }

    }

}
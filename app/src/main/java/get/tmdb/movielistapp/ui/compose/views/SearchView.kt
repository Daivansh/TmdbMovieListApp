package get.tmdb.movielistapp.ui.compose.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import get.tmdb.movielistapp.R
import get.tmdb.movielistapp.ui.theme.Grey200
import get.tmdb.movielistapp.ui.theme.Grey400
import get.tmdb.movielistapp.ui.theme.Grey600
import get.tmdb.movielistapp.ui.theme.White

/**
 * Search view which is used in home listing screen for searching movies
 * It is generic and can be used in any screen for any use case
 *
 * @param modifier Modifier for the search view layout properties
 * @param searchText String for the search text to be displayed in the view
 * @param placeHolder String for the placeholder text as hint
 * @param onTextChange Callback for the text change event in the view
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView(
    modifier : Modifier = Modifier,
    searchText : String,
    placeHolder : String,
    onTextChange : (String) -> Unit,
){

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(dimensionResource(id = R.dimen.corner_radius_medium)))
            .padding(bottom = dimensionResource(id = R.dimen.padding_medium))
            .border(
                BorderStroke(
                    dimensionResource(id = R.dimen.search_border_width),
                    SolidColor(Grey200)
                ),
                RoundedCornerShape(dimensionResource(id = R.dimen.corner_radius_medium))
            ),
        contentAlignment = Alignment.Center
    ) {

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.search_view_height)),
            value = searchText,
            onValueChange = onTextChange,
            placeholder = {
                Text(
                    text = placeHolder,
                    style = MaterialTheme.typography.labelMedium,
                    color = Grey400
                )
            },
            leadingIcon = {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        tint = Grey400,
                        modifier = Modifier.size(22.dp)
                    )
                }
            },
            singleLine = true,
            textStyle = MaterialTheme.typography.labelMedium,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = White,
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                cursorColor = Grey600
            )
        )
    }
}
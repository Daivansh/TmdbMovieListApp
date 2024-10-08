package get.tmdb.movielistapp.ui.compose.navgation

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import get.tmdb.movielistapp.domain.MovieListItem
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * Custom NavType to be used in the used for passing MovieListItem from listing screen to details sceen destinations
 * NavType defines how arguments should be serialized and deserialized when navigating between screens.
 */
object CustomNavType {

    val MovieItemType = object : NavType<MovieListItem>(
        isNullableAllowed = false
    ) {
        override fun get(bundle: Bundle, key: String): MovieListItem? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): MovieListItem {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: MovieListItem): String {
            return Uri.encode(Json.encodeToString(value))
        }

        override fun put(bundle: Bundle, key: String, value: MovieListItem) {
            bundle.putString(key, Json.encodeToString(value))
        }
    }
}
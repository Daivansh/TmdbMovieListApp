package get.tmdb.movielistapp.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import get.tmdb.movielistapp.ui.compose.navgation.MovieAppNavigation
import get.tmdb.movielistapp.ui.theme.TmdbMovieListAppTheme

/**
 * Home listing activity which is the launcher activity of the app
 * It contains the navigation graph which contains all the screens of the app
 * Its a one and only activity in the app
 */
@AndroidEntryPoint
class HomeListingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TmdbMovieListAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MovieAppNavigation()
                }
            }
        }
    }
}
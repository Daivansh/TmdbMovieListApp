package get.tmdb.movielistapp.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Application class for app initialization,
 * It is used as starting point to initialize the Hilt dependency injection
 */
@HiltAndroidApp
class MovieListApplication: Application()
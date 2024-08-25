package get.tmdb.movielistapp.ui.theme

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally

/**
 * Animation to be used in the app for transitions across screens in the navigation graph
 */

val slideInHorizontally = slideInHorizontally(
    initialOffsetX = { 1000 }, // Slide from the right
    animationSpec = tween(700) // Duration for the enter animation
)

val slideOutHorizontally = slideOutHorizontally(
    targetOffsetX = { 1200 }, // Slide to the left
    animationSpec = tween(1200) // Duration for the exit animation
)
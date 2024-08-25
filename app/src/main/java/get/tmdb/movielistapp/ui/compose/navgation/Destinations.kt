package get.tmdb.movielistapp.ui.compose.navgation

sealed class Destinations(val route:String){
    object HomeListingScreen: Destinations("home_listing_screen")
 }

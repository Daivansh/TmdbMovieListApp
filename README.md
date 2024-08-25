# TMDB Movie List Application

TMDB Movie List is an Android application that displays a list of trending movies fetched from The Movie Database (TMDB) API. Users can view detailed information about each movie by clicking on it, search for specific movies by using search bar on listing screen.

## Features
- Displays a list of trending movies on the home screen.
- Provides detailed information about each movie on a separate detail page.
- Allows users to search for movies using a search query.
- Includes error handling with retry functionality.
- Shows a "No movies found" screen when a search query returns no results.
- It avoids multiple api calls when the user types fast using debouncing technique.

## Screen Recording and APK

- [Screen Recording](https://drive.google.com/file/d/1l7PMH3wVFDNBHNQjEXJChPDxOiSYE3QN/view?usp=sharing)
- [Download APK](https://drive.google.com/file/d/1GRybgmW4Pr4LAlEm7eD9x1voI3G8g5t4/view?usp=sharing)

## Tech Stack
The application is built using the following technologies:

- **Jetpack Compose**: Android’s modern toolkit for building native UI.
  - [Jetpack Compose Documentation](https://developer.android.com/jetpack/compose/documentation)

- **Compose Navigation**: Navigation library for Jetpack Compose, facilitating the management of navigation within Compose apps.
  - [Compose Navigation Documentation](https://developer.android.com/jetpack/compose/navigation)

- **MVVM Clean Architecture**: Architectural pattern combining MVVM with Clean Architecture principles to achieve separation of concerns and maintainability.
  - [Guide to MVVM Architecture](https://developer.android.com/topic/architecture) 
  
- **Hilt**: Dependency injection library for Android.
  - [Hilt Documentation](https://developer.android.com/training/dependency-injection/hilt-android)
  
- **Coroutines**: For asynchronous programming in Kotlin.
  - [Kotlin Coroutines Documentation](https://kotlinlang.org/docs/coroutines-overview.html)
  
- **Flows**: A reactive streams API in Kotlin for handling asynchronous data streams.
  - [Kotlin Flow Documentation](https://kotlinlang.org/docs/flow.html)

## API Endpoints
1. **Trending Movies:**
   - URL: `https://api.themoviedb.org/3/trending/movie/week?language=en-US&page=1&api_key=<api_key_here>`
   - Retrieves the list of trending movies for the week.
   
2. **Search Movies:**
   - URL: `https://api.themoviedb.org/3/search/movie?query=<search_query_here>&api_key=<api_key_here>&page=1`
   - Searches for movies based on the provided query.

- [TMDB API Documentation](https://www.themoviedb.org/documentation/api)

## Getting Started

### Prerequisites
- Android Studio 4.2 or later
- API key from [The Movie Database (TMDB)](https://www.themoviedb.org/)

### Installation
1. Clone the repository.
   ```bash
   git clone -b master https://github.com/Daivansh/TmdbMovieListApp
2. Open the project in Android Studio.
3. Updated your TMDB API key in buildConfigField present in the app level build.gradle file.
4. Build and run the app on an Android device or emulator.

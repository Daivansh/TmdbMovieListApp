package get.tmdb.movielistapp.usecase.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import get.tmdb.movielistapp.usecase.TrendingMoviesListUseCase
import get.tmdb.movielistapp.api.repo.MovieRepository
import get.tmdb.movielistapp.usecase.SearchMoviesListUseCase
import get.tmdb.movielistapp.usecase.model.Interactors

@InstallIn(ViewModelComponent::class)
@Module
object UseCaseModule {

    @Provides
    fun interactors(movieRepository: MovieRepository): Interactors = Interactors(
        TrendingMoviesListUseCase(movieRepository),
        SearchMoviesListUseCase(movieRepository),
    )
}

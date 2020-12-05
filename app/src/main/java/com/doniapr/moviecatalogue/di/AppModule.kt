package com.doniapr.moviecatalogue.di

import com.doniapr.core.domain.usecase.MovieInteractor
import com.doniapr.core.domain.usecase.MovieUseCase
import com.doniapr.core.domain.usecase.TvShowInteractor
import com.doniapr.core.domain.usecase.TvShowUseCase
import com.doniapr.moviecatalogue.movie.DetailMovieViewModel
import com.doniapr.moviecatalogue.movie.MovieViewModel
import com.doniapr.moviecatalogue.tvshow.DetailTvShowViewModel
import com.doniapr.moviecatalogue.tvshow.TvShowViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
    factory<TvShowUseCase> { TvShowInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { TvShowViewModel(get()) }
    viewModel { DetailMovieViewModel(get()) }
    viewModel { DetailTvShowViewModel(get()) }
}
package com.doniapr.moviecatalogue.favorite.di

import com.doniapr.moviecatalogue.favorite.ui.MovieFavoriteViewModel
import com.doniapr.moviecatalogue.favorite.ui.TvShowFavoriteViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { MovieFavoriteViewModel(get()) }
    viewModel { TvShowFavoriteViewModel(get()) }
}
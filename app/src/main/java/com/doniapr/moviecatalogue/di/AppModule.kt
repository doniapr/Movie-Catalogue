package com.doniapr.moviecatalogue.di

import com.doniapr.core.domain.usecase.MovieInteractor
import com.doniapr.core.domain.usecase.MovieUseCase
import com.doniapr.moviecatalogue.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}
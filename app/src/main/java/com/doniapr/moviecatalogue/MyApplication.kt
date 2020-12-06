package com.doniapr.moviecatalogue

import android.app.Application
import com.doniapr.core.di.databaseModule
import com.doniapr.core.di.networkModule
import com.doniapr.core.di.repositoryModule
import com.doniapr.moviecatalogue.di.useCaseModule
import com.doniapr.moviecatalogue.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}
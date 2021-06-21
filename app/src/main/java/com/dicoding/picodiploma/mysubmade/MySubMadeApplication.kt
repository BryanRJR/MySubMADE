package com.dicoding.picodiploma.mysubmade

import android.app.Application
import com.dicoding.picodiploma.mysubmade.core.di.databaseModule
import com.dicoding.picodiploma.mysubmade.core.di.networkModule
import com.dicoding.picodiploma.mysubmade.core.di.repositoryModule
import com.dicoding.picodiploma.mysubmade.di.useCaseModule
import com.dicoding.picodiploma.mysubmade.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MySubMadeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MySubMadeApplication)
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
package com.ruskiikot.vepormasevaluacion

import android.app.Application
import com.ruskiikot.shared.di.episodeModule
import com.ruskiikot.shared.di.networkKtorModule
import com.ruskiikot.shared.di.showDetailsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            allowOverride(false)
            androidContext(this@MyApplication)
            modules(
                networkKtorModule,
                episodeModule,
                showDetailsModule,
            )
        }
    }
}

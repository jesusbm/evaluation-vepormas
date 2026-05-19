package com.ruskiikot.vepormasevaluacion

import android.app.Application
import com.ruskiikot.vepormasevaluacion.di.koinInitialization
import org.koin.android.ext.koin.androidContext

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        koinInitialization(
            setContext = { it.androidContext(this@MyApplication) }
        )
    }
}

package com.ruskiikot.vepormasevaluacion.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

fun koinInitialization(setContext: ((KoinApplication) -> Unit)? = null) {
    startKoin {
        allowOverride(false)
        setContext?.invoke(this@startKoin)
        modules(
            networkKtorModule,
            episodeModule,
            showDetailsModule,
        )
    }
}
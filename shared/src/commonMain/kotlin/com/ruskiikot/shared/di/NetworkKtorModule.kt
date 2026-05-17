package com.ruskiikot.shared.di

import com.ruskiikot.vepormasevaluacion.network.TvMazeKtorApiClient
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkKtorModule = module {
    single { getTvMazeKtorApiClient() }
}

private fun getTvMazeKtorApiClient(): TvMazeKtorApiClient {
    val client = getKtorClient()
    return TvMazeKtorApiClient(client)
}

private fun getKtorClient(): HttpClient {
    return HttpClient(engineFactory = OkHttp) {
        defaultRequest {
            url(urlString = "https://api.tvmaze.com")
        }
        install(plugin = ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
        install(Logging)

    }
}

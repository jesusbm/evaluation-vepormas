package com.ruskiikot.vepormasevaluacion.di

import com.ruskiikot.vepormasevaluacion.network.TvMazeKtorApiClient
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
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
    return HttpClient {
        install(plugin = ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                }
            )
        }
        install(Logging) {
            level = LogLevel.ALL
        }
        install(HttpTimeout) {
            requestTimeoutMillis = 15000
        }
        defaultRequest {
            url(urlString = "https://api.tvmaze.com")
            header(
                HttpHeaders.ContentType,
                ContentType.Application.Json,
            )
        }
    }
}

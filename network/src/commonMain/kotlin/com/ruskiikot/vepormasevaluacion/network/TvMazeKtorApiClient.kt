package com.ruskiikot.vepormasevaluacion.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class TvMazeKtorApiClient(private val client: HttpClient) {

    suspend fun getSchedule(country: String, date: String): List<EpisodeResponse> {
        return client.get("schedule?country=${country}&date=${date}").body()
    }

    suspend fun getSearchForShow(query: String): List<ShowSearchResponse> {
        return client.get("search/shows?q=${query}").body()
    }

    suspend fun getShow(showId: Long): ShowResponse {
        return client.get("shows/${showId}").body()
    }

    suspend fun getCastForShow(id: Long): List<ShowCastResponse> {
        return client.get("shows/${id}/cast").body()
    }
}

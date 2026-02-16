package com.ruskiikot.vepormasevaluacion.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvMazeApiClient {

    @GET("schedule")
    suspend fun getSchedule(
        @Query("country") country: String,
        @Query("date") date: String,
    ): Response<List<EpisodeResponse>>

    @GET("search/shows")
    suspend fun getSearchForShow(
        @Query("q") query: String,
    ): Response<List<ShowSearchResponse>>

    @GET("shows/{show_id}")
    suspend fun getShow(
        @Path("show_id")
        showId: Long,
    ): Response<ShowResponse>

    @GET("shows/{show_id}/cast")
    suspend fun getCastForShow(
        @Path("show_id")
        id: Long,
    ): Response<List<ShowCastResponse>>
}

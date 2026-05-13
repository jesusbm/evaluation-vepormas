package com.ruskiikot.vepormasevaluacion.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ShowSearchResponse(
    @SerialName("show")
    val show: ShowSearchDataResponse,
)

@Serializable
data class ShowSearchDataResponse(
    @SerialName("id")
    val id: Long,

    @SerialName("name")
    val name: String,

    @SerialName("network")
    val network: ShowSearchNetworkResponse?,

    @SerialName("schedule")
    val schedule: ShowSearchScheduleResponse,

    @SerialName("image")
    val image: ShowSearchImageResponse?,
)

@Serializable
data class ShowSearchNetworkResponse(
    @SerialName("name")
    val name: String,
)

@Serializable
data class ShowSearchScheduleResponse(
    @SerialName("time")
    val time: String,

    @SerialName("days")
    val days: List<String>,
)

@Serializable
data class ShowSearchImageResponse(
    @SerialName("medium")
    val medium: String,
)

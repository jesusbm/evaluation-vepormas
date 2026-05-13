package com.ruskiikot.vepormasevaluacion.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ShowResponse(
    @SerialName("id")
    val id: Long,

    @SerialName("name")
    val name: String,

    @SerialName("image")
    val image: ShowImageResponse?,

    @SerialName("rating")
    val rating: ShowRatingResponse,

    @SerialName("network")
    val network: ShowNetworkResponse,

    @SerialName("genres")
    val genres: List<String>,

    @SerialName("officialSite")
    val officialSite: String?,

    @SerialName("schedule")
    val schedule: ShowScheduleResponse,

    @SerialName("summary")
    val summary: String,
)

@Serializable
data class ShowImageResponse(
    @SerialName("medium")
    val medium: String,

    @SerialName("original")
    val original: String,
)

@Serializable
data class ShowRatingResponse(
    @SerialName("average")
    val average: String?,
)

@Serializable
data class ShowNetworkResponse(
    @SerialName("name")
    val name: String,
)

@Serializable
data class ShowScheduleResponse(
    @SerialName("time")
    val time: String,

    @SerialName("days")
    val days: List<String>,
)

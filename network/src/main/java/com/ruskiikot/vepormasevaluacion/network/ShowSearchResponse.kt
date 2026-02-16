package com.ruskiikot.vepormasevaluacion.network

import com.google.gson.annotations.SerializedName

data class ShowSearchResponse(
    @SerializedName("show")
    val show: ShowSearchDataResponse,
)

data class ShowSearchDataResponse(
    @SerializedName("id")
    val id: Long,

    @SerializedName("name")
    val name: String,

    @SerializedName("network")
    val network: ShowSearchNetworkResponse?,

    @SerializedName("schedule")
    val schedule: ShowSearchScheduleResponse,

    @SerializedName("image")
    val image: ShowSearchImageResponse?,
)

data class ShowSearchNetworkResponse(
    @SerializedName("name")
    val name: String,
)

data class ShowSearchScheduleResponse(
    @SerializedName("time")
    val time: String,

    @SerializedName("days")
    val days: List<String>,
)

data class ShowSearchImageResponse(
    @SerializedName("medium")
    val medium: String,
)

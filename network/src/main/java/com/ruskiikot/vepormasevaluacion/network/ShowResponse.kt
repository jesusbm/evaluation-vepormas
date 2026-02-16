package com.ruskiikot.vepormasevaluacion.network

import com.google.gson.annotations.SerializedName

data class ShowResponse(
    @SerializedName("id")
    val id: Long,

    @SerializedName("name")
    val name: String,

    @SerializedName("image")
    val image: ShowImageResponse?,

    @SerializedName("rating")
    val rating: ShowRatingResponse,

    @SerializedName("network")
    val network: ShowNetworkResponse,

    @SerializedName("genres")
    val genres: List<String>,

    @SerializedName("officialSite")
    val officialSite: String?,

    @SerializedName("schedule")
    val schedule: ShowScheduleResponse,

    @SerializedName("summary")
    val summary: String,
)

data class ShowImageResponse(
    @SerializedName("medium")
    val medium: String,

    @SerializedName("original")
    val original: String,
)

data class ShowRatingResponse(
    @SerializedName("average")
    val average: String?,
)

data class ShowNetworkResponse(
    @SerializedName("name")
    val name: String,
)

data class ShowScheduleResponse(
    @SerializedName("time")
    val time: String,

    @SerializedName("days")
    val days: List<String>,
)

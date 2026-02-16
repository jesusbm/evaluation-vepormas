package com.ruskiikot.vepormasevaluacion.network

import com.google.gson.annotations.SerializedName

data class EpisodeResponse (
    @SerializedName("id")
    val id: Long,

    @SerializedName("name")
    val name: String,

    @SerializedName("airdate")
    val airdate: String,

    @SerializedName("airtime")
    val airtime: String,

    @SerializedName("show")
    val show: EpisodeShowResponse,
)

data class EpisodeShowResponse(
    @SerializedName("id")
    val id: Long,

    @SerializedName("name")
    val name: String,

    @SerializedName("image")
    val image: EpisodeShowImageResponse?,

    @SerializedName("network")
    val network: EpisodeShowNetworkResponse?,
)

data class EpisodeShowImageResponse(
    @SerializedName("medium")
    val medium: String,
)

data class EpisodeShowNetworkResponse(
    @SerializedName("name")
    val name: String,
)

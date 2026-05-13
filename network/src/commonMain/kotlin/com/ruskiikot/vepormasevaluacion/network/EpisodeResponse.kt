package com.ruskiikot.vepormasevaluacion.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EpisodeResponse (
    @SerialName("id")
    val id: Long,

    @SerialName("name")
    val name: String,

    @SerialName("airdate")
    val airdate: String,

    @SerialName("airtime")
    val airtime: String,

    @SerialName("show")
    val show: EpisodeShowResponse,
)

@Serializable
data class EpisodeShowResponse(
    @SerialName("id")
    val id: Long,

    @SerialName("name")
    val name: String,

    @SerialName("image")
    val image: EpisodeShowImageResponse?,

    @SerialName("network")
    val network: EpisodeShowNetworkResponse?,
)

@Serializable
data class EpisodeShowImageResponse(
    @SerialName("medium")
    val medium: String,
)

@Serializable
data class EpisodeShowNetworkResponse(
    @SerialName("name")
    val name: String,
)

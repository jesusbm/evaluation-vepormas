package com.ruskiikot.vepormasevaluacion.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ShowCastResponse(
    @SerialName("person")
    val person: ShowCastPersonResponse,
)

@Serializable
data class ShowCastPersonResponse(
    @SerialName("name")
    val name: String,

    @SerialName("image")
    val image: ShowCastImageResponse?,
)

@Serializable
data class ShowCastImageResponse(
    @SerialName("medium")
    val medium: String,
)

package com.ruskiikot.vepormasevaluacion.network

import com.google.gson.annotations.SerializedName

data class ShowCastResponse(
    @SerializedName("person")
    val person: ShowCastPersonResponse,
)

data class ShowCastPersonResponse(
    @SerializedName("name")
    val name: String,

    @SerializedName("image")
    val image: ShowCastImageResponse?,
)

data class ShowCastImageResponse(
    @SerializedName("medium")
    val medium: String,
)

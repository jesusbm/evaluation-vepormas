package com.ruskiikot.shared.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface AppScreen {

    @Serializable
    data object EpisodeListing : AppScreen

    @Serializable
    data class ShowDetails(val idShow: Long) : AppScreen
}

package com.ruskiikot.vepormasevaluacion.episodes.presentation

sealed interface EpisodesIntent {
    data object LoadEpisodesIntent : EpisodesIntent
    data class RequestDeleteItemIntent(val idItem: Long) : EpisodesIntent
    data object ConfirmDeleteItemIntent : EpisodesIntent
    data object CancelDeleteItemIntent : EpisodesIntent
}

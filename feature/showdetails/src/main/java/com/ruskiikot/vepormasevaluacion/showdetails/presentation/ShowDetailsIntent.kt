package com.ruskiikot.vepormasevaluacion.showdetails.presentation

sealed interface ShowDetailsIntent {
    data class LoadShowDetails(val showId: Long) : ShowDetailsIntent
}

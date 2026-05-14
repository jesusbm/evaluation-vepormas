package com.ruskiikot.vepormasevaluacion.showdetails.presentation

import com.ruskiikot.vepormasevaluacion.showdetails.domain.model.ShowDetails

data class ShowDetailsUiState(
    val showDetails: ShowDetails,
    val loadingState: LoadingStatusEnum,
    val errorMsg: String?,
) {
    companion object {
        val initialState = ShowDetailsUiState(
            showDetails = ShowDetails(
                id = 0,
                name = "",
                imageMediumUrl = "",
                networkName = "",
                averageRating = "",
                summary = "",
                genres = emptyList(),
                scheduleTime = "",
                scheduleDays = emptyList(),
            ),
            loadingState = LoadingStatusEnum.INITIALIZED,
            errorMsg = null,
        )
    }
}

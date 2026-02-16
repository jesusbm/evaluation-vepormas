package com.ruskiikot.vepormasevaluacion.episodes.presentation

import com.ruskiikot.vepormasevaluacion.episodes.domain.model.Episode

data class EpisodesUiState(
    val episodes: List<Episode>,
    val loadingState: LoadingStatusEnum,
    val isConfirmationDialogOpen: Boolean,
    val idItemSelectedToDelete: Long?,
    val errorMsg: String?,
) {
    companion object {
        val initialState = EpisodesUiState(
            episodes = mutableListOf(),
            loadingState = LoadingStatusEnum.INITIALIZED,
            isConfirmationDialogOpen = false,
            idItemSelectedToDelete = null,
            errorMsg = null,
        )
    }
}

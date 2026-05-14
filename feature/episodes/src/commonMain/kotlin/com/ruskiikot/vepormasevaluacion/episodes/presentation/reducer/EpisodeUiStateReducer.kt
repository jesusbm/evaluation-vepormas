package com.ruskiikot.vepormasevaluacion.episodes.presentation.reducer

import com.ruskiikot.vepormasevaluacion.episodes.presentation.EpisodesUiState

fun EpisodesUiState.showDeleteConfirmationDialog(idItem: Long): EpisodesUiState {
    return this.copy(isConfirmationDialogOpen = true, idItemSelectedToDelete = idItem)
}

fun EpisodesUiState.closeDeleteConfirmationDialog(): EpisodesUiState {
    return this.copy(isConfirmationDialogOpen = false, idItemSelectedToDelete = null)
}

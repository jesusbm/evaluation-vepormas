package com.ruskiikot.vepormasevaluacion.episodes.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruskiikot.vepormasevaluacion.episodes.domain.usecase.DeleteEpisodeFromListingUseCase
import com.ruskiikot.vepormasevaluacion.episodes.domain.usecase.LoadEpisodesForCurrentDayUseCase
import com.ruskiikot.vepormasevaluacion.episodes.presentation.reducer.closeDeleteConfirmationDialog
import com.ruskiikot.vepormasevaluacion.episodes.presentation.reducer.showDeleteConfirmationDialog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodesViewModel @Inject constructor(
    private val loadEpisodesForCurrentDayUseCase: LoadEpisodesForCurrentDayUseCase,
    private val deleteEpisodeFromListingUseCase: DeleteEpisodeFromListingUseCase,
) : ViewModel() {

    val uiState = MutableStateFlow(EpisodesUiState.initialState)

    fun sendIntent(intent: EpisodesIntent) {
        when (intent) {
            is EpisodesIntent.LoadEpisodesIntent -> {
                loadEpisodes()
            }

            is EpisodesIntent.RequestDeleteItemIntent -> {
                uiState.update {
                    it.showDeleteConfirmationDialog(intent.idItem)
                }
            }

            is EpisodesIntent.ConfirmDeleteItemIntent -> {
                viewModelScope.launch {
                    val useCaseResult =
                        deleteEpisodeFromListingUseCase(uiState.value.idItemSelectedToDelete)
                    // no se esta manejando el caso de un error en el borrado de un item
                    uiState.update {
                        it.closeDeleteConfirmationDialog()
                    }
                    if (useCaseResult is DeleteEpisodeFromListingUseCase.UseCaseResult.Success) {
                        println("OKOK")
                        sendIntent(EpisodesIntent.LoadEpisodesIntent)
                    } else {
                        println("ERRORR")
                    }
                }
            }

            is EpisodesIntent.CancelDeleteItemIntent -> {
                uiState.update {
                    it.closeDeleteConfirmationDialog()
                }
            }
        }
    }

    private fun loadEpisodes() {
        viewModelScope.launch {
            uiState.update { it.copy(loadingState = LoadingStatusEnum.LOADING) }
            when (val useCaseResult = loadEpisodesForCurrentDayUseCase()) {
                is LoadEpisodesForCurrentDayUseCase.UseCaseResult.Success -> {
                    uiState.update { current ->
                        current.copy(
                            episodes = useCaseResult.episodes,
                            loadingState = LoadingStatusEnum.LOADED,
                            errorMsg = null,
                        )
                    }
                }

                is LoadEpisodesForCurrentDayUseCase.UseCaseResult.Error -> {
                    uiState.update {
                        EpisodesUiState.initialState.copy(
                            errorMsg = useCaseResult.errorMsg,
                            loadingState = LoadingStatusEnum.LOADED,
                        )
                    }
                }
            }
        }
    }
}

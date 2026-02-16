package com.ruskiikot.vepormasevaluacion.showdetails.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruskiikot.vepormasevaluacion.showdetails.domain.usecase.LoadShowDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowDetailsViewModel @Inject constructor(
    private val loadShowDetailsUseCase: LoadShowDetailsUseCase
) : ViewModel() {

    val uiState = MutableStateFlow(ShowDetailsUiState.initialState)

    fun sendIntent(intent: ShowDetailsIntent) {
        when (intent) {
            is ShowDetailsIntent.LoadShowDetails -> {
                loadShowDetails(intent.showId)
            }
        }
    }

    private fun loadShowDetails(showId: Long) {
        viewModelScope.launch {
            uiState.update { it.copy(loadingState = LoadingStatusEnum.LOADING) }
            when (val useCaseResult = loadShowDetailsUseCase(showId)) {
                is LoadShowDetailsUseCase.UseCaseResult.Success -> {
                    uiState.update { current ->
                        current.copy(
                            showDetails = useCaseResult.showDetails,
                            loadingState = LoadingStatusEnum.LOADED,
                            errorMsg = null,
                        )
                    }
                }

                is LoadShowDetailsUseCase.UseCaseResult.Error -> {
                    uiState.update {
                        ShowDetailsUiState.initialState.copy(
                            errorMsg = useCaseResult.errorMsg,
                            loadingState = LoadingStatusEnum.LOADED,
                        )
                    }
                }
            }
        }
    }
}

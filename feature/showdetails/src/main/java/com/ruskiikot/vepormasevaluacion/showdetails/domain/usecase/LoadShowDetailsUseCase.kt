package com.ruskiikot.vepormasevaluacion.showdetails.domain.usecase

import com.ruskiikot.vepormasevaluacion.showdetails.domain.model.ShowDetails
import com.ruskiikot.vepormasevaluacion.showdetails.domain.repository.ShowDetailsRepository
import javax.inject.Inject

class LoadShowDetailsUseCase @Inject constructor(
    private val showDetailsRepository: ShowDetailsRepository,
) {
    suspend operator fun invoke(showId: Long): UseCaseResult {
        return try {
            val result = showDetailsRepository.getShowDetails(showId)
            UseCaseResult.Success(result)
        } catch (e: Exception) {
            UseCaseResult.Error(e.message ?: "Error desconocido")
        }
    }

    sealed class UseCaseResult {
        data class Success(val showDetails: ShowDetails): UseCaseResult()
        data class Error(val errorMsg: String): UseCaseResult()
    }
}
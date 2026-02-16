package com.ruskiikot.vepormasevaluacion.episodes.domain.usecase

import com.ruskiikot.vepormasevaluacion.episodes.domain.repository.EpisodeRepository
import javax.inject.Inject

class DeleteEpisodeFromListingUseCase @Inject constructor(
    private val episodeRepository: EpisodeRepository
) {
    suspend operator fun invoke(idEpisode: Long?): UseCaseResult {
        if (idEpisode == null) {
            return UseCaseResult.Error("A")
        }
        val idEpisodeDeleted = episodeRepository.deleteEpisode(idEpisode)
        return if (idEpisodeDeleted != null) {
            UseCaseResult.Success(idEpisodeDeleted)
        } else {
            UseCaseResult.Error("B")
        }
    }

    sealed interface UseCaseResult {
        data class Success(val idEpisode: Long) : UseCaseResult
        data class Error(val errorMsg: String) : UseCaseResult
    }
}

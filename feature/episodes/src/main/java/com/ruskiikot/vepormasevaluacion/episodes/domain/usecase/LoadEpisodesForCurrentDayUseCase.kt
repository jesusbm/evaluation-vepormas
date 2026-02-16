package com.ruskiikot.vepormasevaluacion.episodes.domain.usecase

import com.ruskiikot.vepormasevaluacion.episodes.domain.model.Episode
import com.ruskiikot.vepormasevaluacion.episodes.domain.repository.EpisodeRepository
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class LoadEpisodesForCurrentDayUseCase @Inject constructor(
    private val episodeRepository: EpisodeRepository,
) {
    suspend operator fun invoke(): UseCaseResult {
        return try {
            val country = "US"
            val (currentDate, currentDateFormatted) = LocalDate.now().run {
                format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) to
                format(DateTimeFormatter.ofPattern("EEEE dd MMMM, yyyy"))
            }
            val result = episodeRepository.getEpisodes(country, currentDate)
            UseCaseResult.Success(result, currentDateFormatted)
        } catch (e: Exception) {
            UseCaseResult.Error(e.message ?: "Error desconocido")
        }
    }

    sealed class UseCaseResult {
        data class Success(
            val episodes: List<Episode>,
            val currentDateFormatted: String,
        ) : UseCaseResult()
        data class Error(val errorMsg: String) : UseCaseResult()
    }
}

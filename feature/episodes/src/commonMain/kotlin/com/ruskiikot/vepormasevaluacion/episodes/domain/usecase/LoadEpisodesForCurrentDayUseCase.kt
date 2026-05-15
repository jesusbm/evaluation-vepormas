package com.ruskiikot.vepormasevaluacion.episodes.domain.usecase

import com.ruskiikot.vepormasevaluacion.episodes.domain.model.Episode
import com.ruskiikot.vepormasevaluacion.episodes.domain.repository.EpisodeRepository
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(
    ExperimentalTime::class,
    FormatStringsInDatetimeFormats::class,
)
class LoadEpisodesForCurrentDayUseCase constructor(
    private val episodeRepository: EpisodeRepository,
) {
    suspend operator fun invoke(): UseCaseResult {
        return try {
            val country = "US"
            val currentLocalDateTime = Clock.System.now()
                .toLocalDateTime(TimeZone.currentSystemDefault())
            val (currentDate, currentDateFormatted) = currentLocalDateTime.run {
                format(LocalDateTime.Format { byUnicodePattern("yyyy-MM-dd") }) to
                //format(LocalDateTime.Format { byUnicodePattern("EEEE dd MMMM, yyyy") })//EEEE -> nombre del dia, MMMM -> nombre del mes, no soportado en kmp
                format(LocalDateTime.Format { byUnicodePattern("dd MM, yyyy") })//EEEE -> nombre del dia, MMMM -> nombre del mes, no soportado en kmp
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

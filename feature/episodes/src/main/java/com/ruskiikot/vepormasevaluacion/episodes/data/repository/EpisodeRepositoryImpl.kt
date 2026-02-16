package com.ruskiikot.vepormasevaluacion.episodes.data.repository

import com.ruskiikot.vepormasevaluacion.episodes.domain.model.Episode
import com.ruskiikot.vepormasevaluacion.episodes.domain.repository.EpisodeRepository
import com.ruskiikot.vepormasevaluacion.episodes.data.datasource.EpisodesRemoteDataSource
import javax.inject.Inject

class EpisodeRepositoryImpl @Inject constructor(
    val episodesRemoteDataSource: EpisodesRemoteDataSource,
) : EpisodeRepository {

    private var cache: List<Episode>? = null

    override suspend fun getEpisodes(countryCode: String, date: String): List<Episode> {
        cache?.let {
            if (it.isNotEmpty()) {
                return it
            }
        }
        val result = episodesRemoteDataSource.getEpisodes(countryCode, date)
        return result.fold(
            onSuccess = {
                it.filter { it.show.imageMediumUrl.isNotEmpty() }
                    .toMutableList()
                    .also { cache = it }
            },
            onFailure = {
                throw it
            }
        )
    }

    override suspend fun deleteEpisode(idEpisode: Long): Long? {
        val itemFound = cache?.firstOrNull {
            it.show.id == idEpisode
        }
        return if (itemFound != null) {
            cache?.let {
                this.cache = it - itemFound
                idEpisode
            }
        } else {
            null
        }
    }
}

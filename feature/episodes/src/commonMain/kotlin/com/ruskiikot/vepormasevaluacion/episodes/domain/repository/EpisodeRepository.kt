package com.ruskiikot.vepormasevaluacion.episodes.domain.repository

import com.ruskiikot.vepormasevaluacion.episodes.domain.model.Episode

interface EpisodeRepository {

    suspend fun getEpisodes(countryCode: String, date: String): List<Episode>

    suspend fun deleteEpisode(idEpisode: Long): Long?
}
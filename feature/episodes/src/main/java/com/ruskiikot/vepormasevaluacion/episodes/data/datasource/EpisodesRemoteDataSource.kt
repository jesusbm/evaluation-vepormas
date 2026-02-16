package com.ruskiikot.vepormasevaluacion.episodes.data.datasource

import com.ruskiikot.vepormasevaluacion.episodes.domain.model.Episode

interface EpisodesRemoteDataSource {

    suspend fun getEpisodes(countryCode: String, date: String): Result<List<Episode>>
}
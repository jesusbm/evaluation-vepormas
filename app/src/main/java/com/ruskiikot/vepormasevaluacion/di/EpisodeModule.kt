package com.ruskiikot.vepormasevaluacion.di

import com.ruskiikot.vepormasevaluacion.episodes.data.datasource.EpisodesRemoteDataSource
import com.ruskiikot.vepormasevaluacion.episodes.data.datasource.impl.EpisodesRemoteDataSourceImpl
import com.ruskiikot.vepormasevaluacion.episodes.data.repository.EpisodeRepositoryImpl
import com.ruskiikot.vepormasevaluacion.episodes.domain.repository.EpisodeRepository

interface EpisodeModule {

    fun getEpisodeRepository(impl: EpisodeRepositoryImpl): EpisodeRepository

    fun getEpisodesRemoteDataSource(impl: EpisodesRemoteDataSourceImpl): EpisodesRemoteDataSource
}

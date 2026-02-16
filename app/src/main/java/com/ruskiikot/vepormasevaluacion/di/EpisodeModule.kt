package com.ruskiikot.vepormasevaluacion.di

import com.ruskiikot.vepormasevaluacion.episodes.data.datasource.EpisodesRemoteDataSource
import com.ruskiikot.vepormasevaluacion.episodes.data.datasource.impl.EpisodesRemoteDataSourceImpl
import com.ruskiikot.vepormasevaluacion.episodes.data.repository.EpisodeRepositoryImpl
import com.ruskiikot.vepormasevaluacion.episodes.domain.repository.EpisodeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface EpisodeModule {

    @Singleton
    @Binds
    fun getEpisodeRepository(impl: EpisodeRepositoryImpl): EpisodeRepository

    @Binds
    fun getEpisodesRemoteDataSource(impl: EpisodesRemoteDataSourceImpl): EpisodesRemoteDataSource
}

package com.ruskiikot.vepormasevaluacion.di

import com.ruskiikot.vepormasevaluacion.episodes.data.datasource.EpisodesRemoteDataSource
import com.ruskiikot.vepormasevaluacion.episodes.data.datasource.impl.EpisodesRemoteDataSourceImpl
import com.ruskiikot.vepormasevaluacion.episodes.data.repository.EpisodeRepositoryImpl
import com.ruskiikot.vepormasevaluacion.episodes.domain.repository.EpisodeRepository
import com.ruskiikot.vepormasevaluacion.episodes.domain.usecase.DeleteEpisodeFromListingUseCase
import com.ruskiikot.vepormasevaluacion.episodes.domain.usecase.LoadEpisodesForCurrentDayUseCase
import com.ruskiikot.vepormasevaluacion.episodes.presentation.EpisodesViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val episodeModule = module {

    single<EpisodeRepository> {
        EpisodeRepositoryImpl(
            episodesRemoteDataSource = get(),
        )
    }

    single<EpisodesRemoteDataSource> {
        EpisodesRemoteDataSourceImpl(
            apiClient = get(),
        )
    }

    single {
        LoadEpisodesForCurrentDayUseCase(
            episodeRepository = get(),
        )
    }

    single {
        DeleteEpisodeFromListingUseCase(
            episodeRepository = get(),
        )
    }

    viewModelOf(::EpisodesViewModel)
}

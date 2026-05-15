package com.ruskiikot.vepormasevaluacion.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.compose.composable
import androidx.navigation.createGraph
import androidx.navigation.toRoute
import com.ruskiikot.vepormasevaluacion.di.NetworkKtorModule
import com.ruskiikot.vepormasevaluacion.episodes.data.datasource.impl.EpisodesRemoteDataSourceImpl
import com.ruskiikot.vepormasevaluacion.episodes.data.repository.EpisodeRepositoryImpl
import com.ruskiikot.vepormasevaluacion.episodes.domain.usecase.DeleteEpisodeFromListingUseCase
import com.ruskiikot.vepormasevaluacion.episodes.domain.usecase.LoadEpisodesForCurrentDayUseCase
import com.ruskiikot.vepormasevaluacion.episodes.presentation.EpisodesViewModel
import com.ruskiikot.vepormasevaluacion.episodes.ui.EpisodesScreen
import com.ruskiikot.vepormasevaluacion.network.TvMazeKtorApiClient
import com.ruskiikot.vepormasevaluacion.showdetails.data.datasource.impl.ShowDetailsRemoteDataSourceImpl
import com.ruskiikot.vepormasevaluacion.showdetails.data.repository.ShowDetailsRepositoryImpl
import com.ruskiikot.vepormasevaluacion.showdetails.domain.usecase.LoadShowDetailsUseCase
import com.ruskiikot.vepormasevaluacion.showdetails.presentation.ShowDetailsViewModel
import com.ruskiikot.vepormasevaluacion.showdetails.ui.ShowDetailsScreen

@Composable
fun appNavigationGraph(
    navController: NavController,
    startDestination: AppScreen,
    isLandscape: Boolean,
): NavGraph {

    val api: TvMazeKtorApiClient = remember {
        NetworkKtorModule().getTvMazeKtorApiClient()
    }

    return navController.createGraph(startDestination) {
        composable<AppScreen.EpisodeListing> {
            EpisodesScreen(
                viewModel = viewModel {
                    EpisodesViewModel(
                        loadEpisodesForCurrentDayUseCase = LoadEpisodesForCurrentDayUseCase(
                            episodeRepository = EpisodeRepositoryImpl(
                                episodesRemoteDataSource = EpisodesRemoteDataSourceImpl(
                                    apiClient = api
                                )
                            )
                        ),
                        deleteEpisodeFromListingUseCase = DeleteEpisodeFromListingUseCase(
                            episodeRepository = EpisodeRepositoryImpl(
                                episodesRemoteDataSource = EpisodesRemoteDataSourceImpl(
                                    apiClient = api
                                ),
                            ),
                        ),
                    )
                },
                onEpisodeItemClick = {
                    navController.navigate(AppScreen.ShowDetails(it))
                },
            )
        }
        composable<AppScreen.ShowDetails> {
            val argument = it.toRoute<AppScreen.ShowDetails>()
            ShowDetailsScreen(
                viewModel = viewModel {
                    ShowDetailsViewModel(
                        loadShowDetailsUseCase = LoadShowDetailsUseCase(
                            showDetailsRepository = ShowDetailsRepositoryImpl(
                                showDetailsRemoteDataSource = ShowDetailsRemoteDataSourceImpl(
                                    apiClient = api
                                ),
                            ),
                        ),
                    )
                },
                showId = argument.idShow,
                isLandscape = isLandscape,
            )
        }
    }
}

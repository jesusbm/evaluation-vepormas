package com.ruskiikot.vepormasevaluacion.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.compose.composable
import androidx.navigation.createGraph
import androidx.navigation.toRoute
import com.ruskiikot.vepormasevaluacion.episodes.ui.EpisodesScreen
import com.ruskiikot.vepormasevaluacion.showdetails.ui.ShowDetailsScreen
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun appNavigationGraph(
    navController: NavController,
    startDestination: AppScreen,
    isLandscape: Boolean,
): NavGraph {

    return navController.createGraph(startDestination) {
        composable<AppScreen.EpisodeListing> {
            EpisodesScreen(
                viewModel = koinViewModel(),
                onEpisodeItemClick = {
                    navController.navigate(AppScreen.ShowDetails(it))
                },
            )
        }
        composable<AppScreen.ShowDetails> {
            val argument = it.toRoute<AppScreen.ShowDetails>()
            ShowDetailsScreen(
                viewModel = koinViewModel(),
                showId = argument.idShow,
                isLandscape = isLandscape,
            )
        }
    }
}

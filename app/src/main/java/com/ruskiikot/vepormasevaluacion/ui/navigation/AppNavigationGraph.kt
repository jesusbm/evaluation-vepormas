package com.ruskiikot.vepormasevaluacion.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.compose.composable
import androidx.navigation.createGraph
import androidx.navigation.toRoute
import com.ruskiikot.vepormasevaluacion.episodes.ui.EpisodesScreen
import com.ruskiikot.vepormasevaluacion.showdetails.ui.ShowDetailsScreen

@Composable
fun appNavigationGraph(
    navController: NavController,
    startDestination: AppScreen,
    isLandscape: Boolean,
): NavGraph {
    return navController.createGraph(startDestination) {
        composable<AppScreen.EpisodeListing> {
            EpisodesScreen(
                onEpisodeItemClick = {
                    navController.navigate(AppScreen.ShowDetails(it))
                },
            )
        }
        composable<AppScreen.ShowDetails> {
            val argument = it.toRoute<AppScreen.ShowDetails>()
            ShowDetailsScreen(
                showId = argument.idShow,
                isLandscape = isLandscape,
            )
        }
    }
}

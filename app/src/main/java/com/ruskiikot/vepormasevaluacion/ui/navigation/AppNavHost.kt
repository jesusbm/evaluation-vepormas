package com.ruskiikot.vepormasevaluacion.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun AppScreenContainer(
    isLandscape: Boolean,
) {
    val navController: NavHostController = rememberNavController()
    Scaffold { paddingValues ->
        NavHost(
            navController = navController,
            graph = appNavigationGraph(
                navController = navController,
                startDestination = AppScreen.EpisodeListing,
                isLandscape = isLandscape,
            ),
            modifier = Modifier
                .padding(paddingValues)
        )
    }
}

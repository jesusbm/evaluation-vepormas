package com.ruskiikot.vepormasevaluacion

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ruskiikot.vepormasevaluacion.ui.navigation.AppScreenContainer
import com.ruskiikot.vepormasevaluacion.ui.theme.VePorMasEvaluacionTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VePorMasEvaluacionTheme {
                AppScreenContainer(
                    isLandscape = isLandscape(),
                )
            }
        }
    }

    private fun isLandscape(): Boolean {
        return resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    }
}

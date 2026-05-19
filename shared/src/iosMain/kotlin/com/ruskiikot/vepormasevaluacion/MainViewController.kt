package com.ruskiikot.vepormasevaluacion

import androidx.compose.ui.window.ComposeUIViewController
import com.ruskiikot.vepormasevaluacion.di.koinInitialization
import com.ruskiikot.vepormasevaluacion.navigation.AppScreenContainer
import com.ruskiikot.vepormasevaluacion.ui.theme.VePorMasEvaluacionTheme
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    return ComposeUIViewController {
        VePorMasEvaluacionTheme {
            AppScreenContainer(
                isLandscape = false,
            )
        }
    }
}

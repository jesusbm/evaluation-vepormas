plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.android.lint)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.compiler)
}

kotlin {

    androidLibrary {
        namespace = "com.ruskiikot.vepormasevaluacion.uibase"
        compileSdk = 35
        minSdk = 29
    }

    iosArm64()

    iosSimulatorArm64()

    sourceSets {
        commonMain {
            dependencies {

                // compose
                api(libs.compose.lifecycle.runtime)
                api(libs.compose.ui)
                api(libs.compose.ui.graphics)
                api(libs.compose.ui.tooling.preview)
                api(libs.compose.material3)

                // navigation
                api(libs.kotlinx.navigation.compose)

                // coil
                api(libs.coil.compose)
                api(libs.coil.ktor3)
            }
        }
    }
}

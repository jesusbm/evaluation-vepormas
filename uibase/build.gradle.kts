plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.android.lint)
    alias(libs.plugins.jetbrainsCompose)
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
                api(libs.compose.lifecycle.runtime)
                api(libs.compose.ui)
                api(libs.compose.ui.graphics)
                api(libs.compose.ui.tooling.preview)
                api(libs.compose.material3)

                // coil
                api(libs.coil.compose)
                api(libs.coil.ktor3)

                //testImplementation(libs.junit)
                //androidTestImplementation(libs.androidx.junit)
                //androidTestImplementation(libs.androidx.espresso.core)
                //debugImplementation(libs.androidx.ui.tooling)
                //debugImplementation(libs.androidx.ui.test.manifest)
            }
        }
    }
}

dependencies {
    androidRuntimeClasspath(libs.compose.ui.tooling)
}
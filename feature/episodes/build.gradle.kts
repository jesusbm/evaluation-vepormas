plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.android.lint)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.compiler)
}

kotlin {

    androidLibrary {
        namespace = "com.ruskiikot.vepormasevaluacion.episodes"
        compileSdk = 35
        minSdk = 29
        androidResources.enable = true
    }

    iosArm64()

    iosSimulatorArm64()

    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":domain"))
                implementation(project(":uibase"))
                implementation(project(":network"))
                implementation(libs.lifecycle.viewmodel)
                implementation(libs.compose.components.resources)
                implementation(libs.kotlin.datetime)
            }
        }
    }
}

compose.resources {
    publicResClass = false
    packageOfResClass = "com.ruskiikot.vepormasevaluacion.episodes.resources"
    generateResClass = auto
}

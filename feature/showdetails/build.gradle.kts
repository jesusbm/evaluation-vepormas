plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.android.lint)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.compiler)
}

kotlin {

    androidLibrary {
        namespace = "com.ruskiikot.vepormasevaluacion.showdetails"
        compileSdk = 35
        minSdk = 29
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
            }
        }
    }
}

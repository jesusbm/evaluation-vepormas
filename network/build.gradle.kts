plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.android.lint)
    alias(libs.plugins.serialization)
}

kotlin {
    androidLibrary {
        namespace = "com.ruskiikot.vepormasevaluacion.network"
        compileSdk = 35
        minSdk = 29
    }

    iosArm64()

    iosSimulatorArm64()

    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.kotlin.stdlib)
                api(libs.networking.ktor.core)
                api(libs.networking.ktor.contentnegotiation)
                api(libs.networking.ktor.logging)
                api(libs.networking.ktor.serialization.json)
            }
        }

        commonTest {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }

        androidMain {
            dependencies {
                api(libs.networking.ktor.okhttp)
            }
        }

        androidUnitTest {
            dependencies {
                implementation(libs.junit)
            }
        }

        androidInstrumentedTest {
            dependencies {
                implementation(libs.androidx.junit)
                implementation(libs.androidx.espresso.core)
                implementation(libs.androidx.core)
                implementation(libs.androidx.junit)
                implementation(libs.androidx.runner)
            }
        }

        iosMain {
            dependencies {
                api(libs.networking.ktor.darwin)
            }
        }
    }
}

import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)     // Android Entrypoint
    alias(libs.plugins.kotlin.android)          // Android Entrypoint
    alias(libs.plugins.ksp)
    alias(libs.plugins.serialization)
    alias(libs.plugins.compose.multiplatform)   // Support for CMP
    alias(libs.plugins.compose.compiler)        // Support for CMP
}

kotlin {
    dependencies {
        implementation(project(":shared"))
        implementation(libs.androidx.core.ktx)
        implementation(libs.androidx.activity.compose)
    }
    target {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }
}

android {
    namespace = "com.ruskiikot.vepormasevaluacion"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.ruskiikot.claroevaluacion"
        minSdk = 29
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
}

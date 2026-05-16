plugins {
    //alias(libs.plugins.kotlin.multiplatform)
    //alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.serialization)
    alias(libs.plugins.compose.compiler)
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

dependencies {

    implementation(project(":domain"))
    implementation(project(":network"))
    implementation(project(":uibase"))
    implementation(project(":feature:episodes"))
    implementation(project(":feature:showdetails"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)

    // navigation
    implementation(libs.androidx.navigation.compose)

    // serialization
    implementation(libs.kotlinx.serialization)

    // koin
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
    implementation(libs.koin.compose)
    implementation(libs.koin.compose.viewmodel)
    implementation(libs.koin.compose.viewmodel.navigation)

}

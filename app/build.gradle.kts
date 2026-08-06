plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.saikumar.a10minutealarm"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.saikumar.a10minutealarm"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    // Only essential dependencies for the smallest possible APK size
    implementation("androidx.core:core:1.13.1")
}
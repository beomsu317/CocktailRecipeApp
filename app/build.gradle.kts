plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("kotlinx-serialization")
}

android {
    compileSdk = ProjectConfig.compileSdk

    defaultConfig {
        applicationId = ProjectConfig.appId
        minSdk = ProjectConfig.minSdk
        targetSdk = ProjectConfig.targetSdk
        versionCode = ProjectConfig.versionCode
        versionName = ProjectConfig.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Compose.composeCompilerVersion
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(AndroidX.coreKtx)

    implementation(Compose.ui)
    implementation(Compose.material)
    implementation("androidx.compose.ui:ui-util:1.1.1")
    implementation(Compose.uiTooling)
    implementation(Compose.uiToolingPreview)
    implementation(Compose.activityCompose)
    implementation(Compose.viewModelCompose)
    implementation(Compose.hiltNavigationCompose)
    implementation(Compose.navigation)

    testImplementation(Testing.junit4)
    androidTestImplementation(Testing.junitAndroidExt)
    implementation(DaggerHilt.hiltAndroid)
    kapt(DaggerHilt.hiltCompiler)

    implementation(Serialization.kotlinxSerialization)

    implementation(Network.retrofit)
    implementation("com.squareup.okhttp3:okhttp:4.9.3")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")

    implementation("com.google.accompanist:accompanist-systemuicontroller:0.24.5-alpha")
    implementation("com.google.accompanist:accompanist-swiperefresh:0.24.5-alpha")

    implementation("io.coil-kt:coil-compose:2.0.0-rc02")

    implementation("com.google.accompanist:accompanist-pager:0.19.0")
    implementation("com.google.accompanist:accompanist-pager-indicators:0.19.0")

    implementation("org.jetbrains.kotlin:kotlin-reflect:1.6.20")

    implementation("com.google.accompanist:accompanist-placeholder-material:0.24.5-alpha")
}
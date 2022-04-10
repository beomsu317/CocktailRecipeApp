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
    implementation(Compose.uiUtil)
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
    implementation(Serialization.retrofit2KotlinxSerializationConverter)

    implementation(Network.retrofit)
    implementation(Network.okhttp)

    implementation(Accompanist.systemUiController)
    implementation(Accompanist.pager)
    implementation(Accompanist.pagerIndicator)
    implementation(Accompanist.placeholderMaterial)

    implementation(Coil.coil)

    implementation(Room.roomRuntime)
    implementation(Room.roomKtx)
    annotationProcessor(Room.roomCompiler)
    kapt(Room.roomCompiler)
    implementation(Room.runtimeLivedata)

    implementation(Android.palette)

}
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    kotlin("plugin.serialization") version "2.0.0"
    id("com.google.gms.google-services")
}

android {
    namespace = "com.fyp.sctsma"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.fyp.sctsma"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.5"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
//material 2 icons extended
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    //voyager dependencies

    implementation(libs.voyager.navigator)

    // Screen Model
    implementation(libs.voyager.screenmodel)

    // BottomSheetNavigator
    implementation(libs.voyager.bottom.sheet.navigator)

    // TabNavigator
    implementation(libs.voyager.tab.navigator)

    // Transitions
    implementation(libs.voyager.transitions)

    //Ktor
//    implementation (libs.ktor.client.core)
//    implementation (libs.ktor.client.android)
//    implementation (libs.ktor.client.serialization)
//    implementation(libs.ktor.client.content.negotiation)
//    implementation(libs.ktor.serialization.kotlinx.json)
//    implementation (libs.ktor.client.logging)
    //retrofit

    implementation(libs.retrofit)
    //serialization
    implementation(libs.converter.moshi)

    implementation (libs.logback.classic)

    implementation (libs.kotlinx.serialization.json)
    implementation(libs.androidx.runtime.livedata)
    implementation (libs.okhttp)
    implementation(libs.converter.gson)
    implementation(libs.converter.moshi)
    implementation(libs.logging.interceptor)
    implementation( libs.kotlin.reflect)
    implementation(libs.moshi.kotlin)

    implementation(libs.androidx.compiler)
    implementation(libs.androidx.navigation.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //constraint Layout dependencies
    implementation(libs.androidx.constraintlayout)
    implementation (libs.androidx.constraintlayout.compose)
    implementation (libs.androidx.constraintlayout.core)

    //work runtime dependencies
    implementation("androidx.work:work-runtime-ktx:2.9.0")
    //coil image dependency
    implementation("io.coil-kt:coil-compose:2.2.2")

    implementation( libs.hilt.android)
   // kapt( "com.google.dagger:hilt-android-compiler:2.44")

    implementation(platform("com.google.firebase:firebase-bom:33.1.1"))
    implementation("com.google.firebase:firebase-messaging")

}
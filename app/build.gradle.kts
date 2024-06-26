plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")
    id("org.jetbrains.kotlin.plugin.serialization")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.sparklead.petsync"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.sparklead.petsync"
        minSdk = 26
        targetSdk = 34
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
        viewBinding = true
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.5")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //circular image
    implementation("de.hdodenhof:circleimageview:3.1.0")

    //glide library
    implementation("com.github.bumptech.glide:glide:4.15.1")
    annotationProcessor("com.github.bumptech.glide:compiler:4.13.0")

    implementation("com.facebook.shimmer:shimmer:0.1.0@aar")

    implementation("io.github.krupen:fabulousfilter:0.0.6")

    //qr scanner
    implementation("com.github.yuriy-budiyev:code-scanner:2.3.2")

    // Ktor dependencies
    implementation("io.ktor:ktor-client-core:1.6.3")
    implementation("io.ktor:ktor-client-android:1.6.3")
    implementation("io.ktor:ktor-client-serialization:1.6.3")
    implementation("io.ktor:ktor-client-logging:1.6.3")
    implementation("ch.qos.logback:logback-classic:1.2.3")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")

    // Hilt dependencies
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.47")

    // lottie dependency
    implementation("com.airbnb.android:lottie:5.2.0")

    // coroutine dependency
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // datastore dependency
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    implementation("com.github.abdularis:TapHoldUpButton:0.1.2")


    implementation(platform("com.google.firebase:firebase-bom:32.7.0"))
    implementation("com.google.firebase:firebase-analytics")

    // firebase cloud messaging
    implementation("com.google.firebase:firebase-messaging:23.3.1")

    implementation("com.github.ZEGOCLOUD:zego_uikit_prebuilt_call_android:+")

    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")

    // ViewModel utilities for Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")

    //Material3 icons
    implementation("androidx.compose.material:material-icons-extended:1.5.4")

    // Jetpack Compose
    implementation("androidx.compose.material:material:1.6.5")
    implementation("androidx.compose.compiler:compiler:1.5.11")
    implementation("androidx.compose.ui:ui-tooling-preview:1.6.5")
    implementation("androidx.activity:activity-compose:1.8.2")
    debugImplementation("androidx.compose.ui:ui-tooling:1.6.5")
    implementation("androidx.compose.material3:material3:1.2.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
    implementation("androidx.compose.material:material-icons-extended:1.6.5")

    //
    implementation("androidx.compose.material:material:1.6.5")
}
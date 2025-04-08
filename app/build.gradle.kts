plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.gms)
}

android {
    namespace = "com.glyadgzl.nearbarber"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.glyadgzl.nearbarber"
        minSdk = 24
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.play.services.maps)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation("com.google.ai.client.generativeai:generativeai:0.9.0")


    implementation("io.coil-kt:coil-compose:2.2.2")
implementation("com.google.accompanist:accompanist-pager-indicators:0.28.0")
implementation("com.google.accompanist:accompanist-pager:0.28.0")
implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
implementation("androidx.compose.runtime:runtime-livedata:1.7.8")
implementation("com.github.bumptech.glide:glide:4.13.0")
implementation("com.google.code.gson:gson:2.10.1")
implementation("androidx.constraintlayout:constraintlayout-compose:1.1.1")
implementation("androidx.compose.foundation:foundation:1.7.8")
implementation("com.google.accompanist:accompanist-systemuicontroller:0.36.0")
implementation ("com.google.maps.android:maps-compose:2.0.0")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
    implementation("androidx.compose.material:material-icons-extended:1.5.4")
    implementation ("androidx.navigation:navigation-compose:2.7.7")
    implementation ("com.google.android.gms:play-services-maps:17.0.1")


    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.google.firebase.database)



}
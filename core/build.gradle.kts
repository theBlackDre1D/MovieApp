plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlinx.serialozation)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "co.init.core"
    compileSdk = 34

    defaultConfig {
        minSdk = 29

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        val apiKey: String = project.findProperty("API_KEY") as String? ?: ""
        buildConfigField("String", "API_KEY", apiKey)
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
        buildConfig = true
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation (libs.androidx.lifecycle.viewmodel.compose)
    implementation (libs.ui)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose)

    // Gson
    implementation(libs.converter.gson)

    // Coroutines
    implementation(libs.coroutines)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    // Serialization
    implementation(libs.kotlinx.serialization.json)
}
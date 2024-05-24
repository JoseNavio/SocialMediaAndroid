
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id ("com.google.dagger.hilt.android")
    kotlin("kapt")
}

android {
    namespace = "com.navio.socialmedia"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.navio.socialmedia"
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
            isMinifyEnabled = true
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
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    kapt {
        correctErrorTypes = true
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    //Material
    implementation("androidx.compose.material:material:1.0.0")
    //Icons
    implementation("androidx.compose.material:material-icons-extended:1.6.5")
    //Constraint
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")
    //Navigation
    implementation("androidx.navigation:navigation-compose:2.7.7")
    //LiveData
    implementation("androidx.compose.runtime:runtime-livedata:1.6.7")
    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    //Gson
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
	//Hilt
    implementation("com.google.dagger:hilt-android:2.51.1")
    kapt ("com.google.dagger:hilt-compiler:2.51.1")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
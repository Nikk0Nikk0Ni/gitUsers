plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.kapt)
    alias(libs.plugins.safeArgs)
}

android {
    namespace = "com.niko.githubusers"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.niko.githubusers"
        minSdk = 27
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures{
        viewBinding = true
    }
}

dependencies {

    //pagging
    implementation(libs.pagging)

    //OkHttp
    implementation(libs.okHttp)
    implementation(libs.interceptor)

    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.gsonConverter)

    //Coroutines
    implementation(libs.coroutines)

    //Navigations
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)

    //ViewModel
    implementation(libs.view.model)
    kapt(libs.lifecycle)

    //Picasso
    implementation(libs.picasso)

    //SwipeRefresh
    implementation(libs.swiperefreshlay)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
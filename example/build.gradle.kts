import org.gradle.api.JavaVersion.VERSION_1_8

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(rootProject.extra["sdkVersion"] as Int)

    defaultConfig {
        applicationId = "com.xwray.groupie.example"
        minSdkVersion(rootProject.extra["minimumSdkVersion"] as Int)
        targetSdkVersion(rootProject.extra["sdkVersion"] as Int)
        vectorDrawables.useSupportLibrary = true
    }
    lint {
        abortOnError = false
    }
    compileOptions {
        sourceCompatibility = VERSION_1_8
        targetCompatibility = VERSION_1_8
    }
}

dependencies {
    implementation(project(":library"))

    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.1")
    implementation("androidx.preference:preference:1.2.0")
    implementation("androidx.recyclerview:recyclerview:1.2.1")
    implementation("androidx.vectordrawable:vectordrawable-animated:1.1.0")
    implementation("androidx.vectordrawable:vectordrawable:1.1.0")
    implementation("com.google.android.material:material:1.4.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${rootProject.extra["kotlin_version"]}")
}

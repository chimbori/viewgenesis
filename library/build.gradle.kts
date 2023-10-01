plugins {
    id("com.android.library")
    id("kotlin-android")
}

val archivesBaseName = "groupie"
val group = "com.github.lisawray.groupie"
val version = "2.10.1"

android {
    namespace = "com.xwray.groupie"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    signingConfigs {
        register("release") {
        }
    }
    buildTypes {
        named("release") {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = false
        }
    }
    lint {
        abortOnError = false
    }
}

dependencies {
    implementation("androidx.recyclerview:recyclerview:1.2.1")
    implementation("androidx.annotation:annotation:1.3.0")

    testImplementation(libs.junit)
    testImplementation(libs.mockito)
}

plugins {
  id("com.android.library")
  id("kotlin-android")
}

val archivesBaseName = "groupie"
val group = "com.github.chimbori.groupie"
val version = "2.10.1"

android {
  namespace = "com.chimbori.groupie"
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
  implementation(libs.androidx.recyclerview)
  implementation(libs.androidx.annotation)

  testImplementation(libs.junit)
  testImplementation(libs.mockito)
}

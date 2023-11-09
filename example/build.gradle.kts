import org.gradle.api.JavaVersion.VERSION_17

plugins {
  id("com.android.application")
  id("kotlin-android")
  id("kotlin-kapt")
}

android {
  namespace = "com.chimbori.viewgenesis.example"
  compileSdk = libs.versions.compileSdk.get().toInt()

  defaultConfig {
    applicationId = "com.chimbori.viewgenesis.example"
    targetSdk = libs.versions.targetSdk.get().toInt()
    minSdk = libs.versions.minSdk.get().toInt()
    vectorDrawables.useSupportLibrary = true
  }
  lint {
    abortOnError = false
  }
  compileOptions {
    sourceCompatibility = VERSION_17
    targetCompatibility = VERSION_17
  }
  kotlinOptions {
    jvmTarget = "17"
  }
}

dependencies {
  implementation(project(":library"))

  implementation(libs.androidx.appcompat)
  implementation(libs.androidx.cardview)
  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.lifecycle.runtime)
  implementation(libs.androidx.preference)
  implementation(libs.androidx.recyclerview)
  implementation(libs.androidx.vectordrawable)
  implementation(libs.androidx.vectordrawable.animated)
  implementation(libs.kotlin.stdlib)
  implementation(libs.material)
}

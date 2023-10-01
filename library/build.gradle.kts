plugins {
    id("com.android.library")
    id("kotlin-android")
}

val archivesBaseName = "groupie"
val group = "com.github.lisawray.groupie"
val version = "2.10.1"

android {
    namespace = "com.xwray.groupie"
    compileSdk = rootProject.extra["sdkVersion"] as Int

    defaultConfig {
        minSdk = rootProject.extra["minimumSdkVersion"] as Int
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
    testImplementation("junit:junit:${rootProject.extra["junit_version"]}")
    testImplementation("org.mockito:mockito-core:${rootProject.extra["mockito_version"]}")
    compileOnly("androidx.databinding:viewbinding:${rootProject.extra["viewbinding_version"]}") {
        isTransitive = false
    }
    implementation("androidx.annotation:annotation:1.3.0")
}

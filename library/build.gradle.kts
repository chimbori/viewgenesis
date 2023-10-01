plugins {
    id("com.android.library")
    id("kotlin-android")
}

val archivesBaseName = "groupie"
val group = "com.github.lisawray.groupie"
val version = "2.10.1"

android {
    compileSdkVersion(rootProject.extra["sdkVersion"] as Int)

    defaultConfig {
        minSdkVersion(rootProject.extra["minimumSdkVersion"] as Int)
        targetSdkVersion(rootProject.extra["sdkVersion"] as Int)
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

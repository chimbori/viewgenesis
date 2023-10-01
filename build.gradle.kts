// Top-level build file where you can add configuration options common to all sub-projects/modules.

val lintReportsDir = file("${rootProject.buildDir}/lint-reports")
val testReportsDir = "${rootProject.buildDir}/test-reports"
val testResultsDir = "${rootProject.buildDir}/test-results"

buildscript {
  extra["kotlin_version"] = "1.9.10"
  extra["android_plugin_version"] = "8.1.2"
  extra["sdkVersion"] = 33
  extra["minimumSdkVersion"] = 14
  extra["viewbinding_version"] = "7.4.2"

  extra["junit_version"] = "4.13.2"
  extra["mockito_version"] = "3.3.3"

  repositories {
    google()
    mavenCentral()
    maven("https://jitpack.io")
  }
  dependencies {
    classpath("com.android.tools.build:gradle:${rootProject.extra["android_plugin_version"]}")
    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${rootProject.extra["kotlin_version"]}")
    classpath("org.jetbrains.dokka:dokka-android-gradle-plugin:0.9.18")
    // NOTE: Do not place your application dependencies here; they belong
    // in the individual module build.gradle files
  }
}

allprojects {
  repositories {
    google()
    mavenCentral()
    maven("https://jitpack.io")
  }
}

tasks.register<Delete>("clean").configure {
  delete(rootProject.layout.buildDirectory)
}

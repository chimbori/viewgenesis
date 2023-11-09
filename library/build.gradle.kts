plugins {
  id("com.android.library")
  id("kotlin-android")
  id("maven-publish")
}

android {
  namespace = "com.chimbori.viewgenesis"
  compileSdk = libs.versions.compileSdk.get().toInt()

  defaultConfig {
    minSdk = libs.versions.minSdk.get().toInt()
    aarMetadata {
      minCompileSdk = libs.versions.compileSdk.get().toInt()
    }
    publishing {
      multipleVariants {
        allVariants()
        withJavadocJar()
        withSourcesJar()
      }
    }
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

publishing {
  publications {
    register<MavenPublication>("release") {
      groupId = libs.versions.publish.group.get()
      artifactId = libs.versions.publish.artifact.get()
      version = libs.versions.publish.version.get()
      afterEvaluate {
        from(components["release"])
      }
      pom {
        name.set(libs.versions.publish.name.get())
        description.set(libs.versions.publish.description.get())
        url.set(libs.versions.publish.url.get())
        licenses {
          license {
            name.set("MIT License")
            url.set("https://github.com/chimbori/viewgenesis/blob/main/LICENSE.md")
          }
        }
      }
    }
  }
  repositories {
    maven {
      name = "local"
      url = uri(project.layout.buildDirectory.dir("repo").get())
    }
  }
}

dependencies {
  implementation(libs.androidx.recyclerview)
  implementation(libs.androidx.annotation)

  testImplementation(libs.junit)
  testImplementation(libs.mockito)
}

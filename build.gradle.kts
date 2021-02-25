import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  java
  kotlin("jvm") version "1.4.10"
  kotlin("plugin.serialization") version "1.4.10"
}

group = "thuytrinh"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
}

dependencies {
  implementation(kotlin("stdlib"))
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.9")
  implementation("com.squareup.moshi:moshi-kotlin:1.9.3")
  implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.0-RC2")
  implementation("com.squareup.okio:okio:2.8.0")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.4.2")

  testImplementation("junit", "junit", "4.12")
  testImplementation("org.assertj:assertj-core:3.17.2")
}

tasks.withType<KotlinCompile>().all {
  kotlinOptions.freeCompilerArgs += listOf(
    "-Xopt-in=kotlin.time.ExperimentalTime",
    "-Xopt-in=kotlin.ExperimentalUnsignedTypes",
    "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
    "-Xopt-in=kotlinx.coroutines.FlowPreview"
  )
}

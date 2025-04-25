plugins {
    `kotlin-dsl`
}

group = "com.kmptuner.buildlogic"

// Make version catalog available to convention plugins
// See: https://github.com/gradle/gradle/issues/15383
@Suppress("UnstableApiUsage")
dependencies {
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.android.gradlePlugin)
}

kotlin {
    // Add access to Gradle libs through generated accessors in Kotlin DSL convention plugins
    jvmToolchain(17)
}

// Expose the version catalog to the convention plugins
// See: https://github.com/gradle/gradle/issues/15383
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

gradlePlugin {
    plugins {
        register("kotlinMultiplatformConvention") {
            id = "kmptuner.multiplatform"
            implementationClass = "com.kmptuner.buildlogic.plugins.KotlinMultiplatformConventionPlugin"
        }
        register("androidAppConvention") {
            id = "kmptuner.android.app"
            implementationClass = "com.kmptuner.buildlogic.plugins.AndroidAppConventionPlugin"
        }
    }
}

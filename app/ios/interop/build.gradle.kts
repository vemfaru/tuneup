import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    id("kmptuner.multiplatform")
}

kotlin {
    val xcFramework = XCFramework(xcFrameworkName = "KotlinShared")

    listOf(
        iosX64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "KotlinShared"
            isStatic = true

            xcFramework.add(this)
        }
    }

    sourceSets.iosMain.dependencies {
        api(projects.core.designSystem)
        api(projects.shared)
    }
}
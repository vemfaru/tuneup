import com.kmptuner.buildlogic.plugins.composeMultiplatform

plugins {
    id("kmptuner.multiplatform")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.designSystem)
        }
    }
}

composeMultiplatform()
import com.kmptuner.buildlogic.plugins.composeMultiplatform

plugins {
    id("kmptuner.multiplatform")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(libs.compose.ui)
            api(libs.compose.foundation)
            api(libs.compose.material3)
        }
        androidMain.dependencies {
            api(libs.compose.ui.tooling)
            api(libs.compose.ui.tooling.preview)
        }
    }
}

composeMultiplatform()
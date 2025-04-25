plugins {
    id("kmptuner.multiplatform")
}

kotlinMultiplatform {
    composeMultiplatform()
    common {
        main.dependencies {
            implementation(projects.core.designSystem)
            implementation(libs.compose.ui)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.runtime)
        }
        test.dependencies { }
    }

    android {
        main.dependencies {
            implementation(libs.compose.ui.tooling)
            implementation(libs.compose.ui.tooling.preview)
        }
        test.dependencies { }
    }

    ios {
        main.dependencies { }
        test.dependencies { }
    }

    desktop {
        main.dependencies { }
        test.dependencies { }
    }
}
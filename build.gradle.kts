plugins {
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.compose.compiler) apply false
    id("kmptuner.multiplatform") apply false
}

installDefaults()
// Example of how to use the DSL in a KMP module
// This is just for demonstration purposes
// In a real project, this would be in a subproject's build.gradle.kts
/*
plugins {
    id("kmptuner.multiplatform")
}

kotlinMultiplatform {
    common {
        main.dependencies {
            // Common main dependencies will go here
        }
        test.dependencies {
            // Common test dependencies will go here
        }
    }

    android {
        main.dependencies {
            // Android main dependencies will go here
        }
        test.dependencies {
            // Android test dependencies will go here
        }
    }

    ios {
        main.dependencies {
            // iOS main dependencies will go here
        }
        test.dependencies {
            // iOS test dependencies will go here
        }
    }

    desktop {
        main.dependencies {
            // Desktop main dependencies will go here
        }
        test.dependencies {
            // Desktop test dependencies will go here
        }
    }
}
*/

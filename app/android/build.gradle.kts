plugins {
    kotlin("android")
    id("kmptuner.android.app")
}

dependencies {
    implementation(projects.shared)
    implementation(projects.core.designSystem)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.foundation)
    implementation(libs.compose.material3)
    implementation(libs.androidx.activity.compose)
}
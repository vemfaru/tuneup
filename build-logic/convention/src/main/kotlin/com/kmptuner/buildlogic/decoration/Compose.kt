package com.kmptuner.buildlogic.decoration

import com.kmptuner.buildlogic.ext.commonExtension
import com.kmptuner.buildlogic.ext.composePreview
import com.kmptuner.buildlogic.ext.composeUiTooling
import com.kmptuner.buildlogic.ext.debugImplementation
import com.kmptuner.buildlogic.ext.isMultiplatform
import com.kmptuner.buildlogic.ext.kmpAwareImplementation
import com.kmptuner.buildlogic.ext.libs
import com.kmptuner.buildlogic.options.compose.ComposeOptions
import com.kmptuner.buildlogic.options.compose.ComposeOptionsBuilder
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.findByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

fun Project.setupCompose(
    composeOptions: ComposeOptions
) {
    println("setupCompose() called with options: $composeOptions")
    setupComposeCompiler()
    println("setupComposeCompiler() called")

//    setupComposeTooling(true)
//    println("setupComposeTooling() called with enableTooling: ${composeOptions.enableTooling}")
    setupComposeRuntime()
    println("setupComposeRuntime() called")
    setupAndroidCompose()
    println("setupAndroidCompose() called")
}

/**
 * Sets up the Android Compose feature for the project.
 * */
fun Project.setupAndroidCompose() {
    if (commonExtension == null) {
        pluginManager.apply("com.android.library")
    }
    commonExtension?.buildFeatures?.compose = true
}

/**
 * Sets up the Compose compiler plugin for the project.
 * Uses the new approach for the Compose compiler plugin.
 */
private fun Project.setupComposeCompiler() {
    pluginManager.apply("org.jetbrains.kotlin.plugin.compose")
}

/**
 * Sets up Compose tooling for the project.
 * This includes preview and other tooling features.
 */
private fun Project.setupComposeTooling(enableTooling: Boolean) {
    // TODO: Implement the setup for Compose tooling
}

/**
 * Sets up Compose runtime dependencies for the project.
 */
private fun Project.setupComposeRuntime() {
    extensions.findByType<KotlinMultiplatformExtension>()?.run {
        sourceSets.getByName("commonMain").dependencies {
            implementation("org.jetbrains.compose.runtime:runtime:1.8.0-beta02")
        }
    }
}

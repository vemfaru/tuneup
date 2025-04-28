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

/**
 * Applies the Compose Multiplatform configuration to the project.
 *
 * This function sets up the necessary dependencies and configurations for using Jetpack Compose
 * in a multiplatform project.
 *
 * @param composeOptionsBuilder A lambda function to configure the [ComposeOptions].
 */
fun Project.composeMultiplatform(
    composeOptionsBuilder: ComposeOptionsBuilder = {
        ComposeOptions.Builder().build()
    }
) {
    runCatching {
        val composeOptions = ComposeOptions.Builder()
            .apply(composeOptionsBuilder)
            .build()

        project.setupCompose(composeOptions)
    }.onFailure {
        logger.error("Failed to configure Compose Multiplatform: ${it.message}")
    }
}

internal fun Project.setupCompose(
    composeOptions: ComposeOptions
) {
    setupComposeCompiler()
    setupComposeTooling(composeOptions.enableTooling)
    setupComposeRuntime()
    setupAndroidCompose()
}

/**
 * Sets up the Android Compose feature for the project.
 * */
fun Project.setupAndroidCompose() {
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
            val composeVersion = libs.findVersion("compose").get()
            implementation("org.jetbrains.compose.runtime:runtime:$composeVersion")
        }
    }
}

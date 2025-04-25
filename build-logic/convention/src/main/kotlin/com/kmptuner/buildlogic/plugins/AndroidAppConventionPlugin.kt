package com.kmptuner.buildlogic.plugins

import com.kmptuner.buildlogic.decoration.applyAndroidApp
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * A plugin that configures an Android application.
 *
 * This plugin applies the Android application plugin and configures it with the project's
 * Android app options.
 */
class AndroidAppConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            // Apply the Android app configuration
            applyAndroidApp()
        }
    }
}
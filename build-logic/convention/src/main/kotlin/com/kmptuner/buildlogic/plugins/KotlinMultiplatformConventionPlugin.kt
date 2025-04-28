package com.kmptuner.buildlogic.plugins

import com.kmptuner.buildlogic.decoration.applyAndroidLibrary
import com.kmptuner.buildlogic.decoration.setupCompose
import com.kmptuner.buildlogic.options.compose.ComposeOptions
import com.kmptuner.buildlogic.options.compose.ComposeOptionsBuilder
import org.gradle.api.Action
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.model.ObjectFactory
import org.gradle.kotlin.dsl.findByType
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.newInstance
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import javax.inject.Inject

// Used by reflection
@Suppress("unused")
class KotlinMultiplatformConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.jetbrains.kotlin.multiplatform")

            extensions.findByType<KotlinMultiplatformExtension>()?.run {
                applyDefaultHierarchyTemplate()

                applyAndroidLibrary()

                androidTarget()
                iosArm64()
                iosSimulatorArm64()
                jvm("desktop")

            }
        }
    }
}

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
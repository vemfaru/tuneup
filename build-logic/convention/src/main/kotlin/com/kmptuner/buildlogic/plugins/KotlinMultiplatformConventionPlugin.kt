package com.kmptuner.buildlogic.plugins

import com.kmptuner.buildlogic.decoration.applyAndroidLibrary
import com.kmptuner.buildlogic.decoration.setupCompose
import com.kmptuner.buildlogic.options.compose.ComposeOptions
import com.kmptuner.buildlogic.options.compose.ComposeOptionsBuilder
import org.gradle.api.Action
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.model.ObjectFactory
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

            // Get the KotlinMultiplatformExtension and apply default hierarchy template
            extensions.getByType<KotlinMultiplatformExtension>().applyDefaultHierarchyTemplate()

            // Create the KmpDsl extension with the project parameter
            extensions.create("kmpDsl", KmpDsl::class.java, project)
        }
    }
}

/**
 * This class is used to configure the Kotlin Multiplatform DSL.
 *
 * It provides methods to configure common, Android, iOS, and Desktop targets.
 *
 * @param objects The ObjectFactory instance used to create new instances of classes.
 * @param project The Project instance representing the current project.
 */
open class KmpDsl @Inject constructor(
    private val objects: ObjectFactory,
    private val project: Project
) {
    private val kotlinMultiplatformExtension: KotlinMultiplatformExtension
        get() = project.extensions.getByType()

    fun common(action: Action<CommonTarget>) {
        // Common target is always configured by default
        val target = objects.newInstance<CommonTarget>(kotlinMultiplatformExtension)
        action.execute(target)
    }

    fun android(action: Action<AndroidTarget>) = runCatching {
        // Configure Android library with our extension function
        project.applyAndroidLibrary()

        // Configure Android target in Kotlin Multiplatform
        kotlinMultiplatformExtension.androidTarget()

        val target = objects.newInstance<AndroidTarget>(kotlinMultiplatformExtension)
        action.execute(target)
    }.onFailure {
        project.logger.error("Failed to configure Android target: ${it.message}")
    }

    fun ios(action: Action<IosTarget>) = runCatching {
        // Configure iOS targets
        kotlinMultiplatformExtension.apply {
            iosArm64()
            iosSimulatorArm64()
        }

        val target = objects.newInstance<IosTarget>(kotlinMultiplatformExtension)
        action.execute(target)
    }.onFailure {
        project.logger.error("Failed to configure iOS targets: ${it.message}")
    }

    fun desktop(action: Action<DesktopTarget>) = runCatching {
        // Configure JVM target
        kotlinMultiplatformExtension.jvm("desktop")

        val target = objects.newInstance<DesktopTarget>(kotlinMultiplatformExtension)
        action.execute(target)
    }.onFailure {
        project.logger.error("Failed to configure Desktop/JVM target: ${it.message}")
    }

    fun composeMultiplatform(composeOptionsBuilder: ComposeOptionsBuilder = { ComposeOptions.Builder().build() }) {
        runCatching {
            val composeOptions = ComposeOptions.Builder()
                .apply(composeOptionsBuilder)
                .build()

            project.setupCompose(composeOptions)
        }.onFailure {
            project.logger.error("Failed to configure Compose Multiplatform: ${it.message}")
        }
    }
}

abstract class PlatformTarget @Inject constructor(
    objects: ObjectFactory,
    kotlinExtension: KotlinMultiplatformExtension,
    mainSourceSetName: String,
    testSourceSetName: String
) {
    val main = objects.newInstance<SourceSetConfiguration>(kotlinExtension, mainSourceSetName)
    val test = objects.newInstance<SourceSetConfiguration>(kotlinExtension, testSourceSetName)
}

open class CommonTarget @Inject constructor(
    objects: ObjectFactory,
    kotlinExtension: KotlinMultiplatformExtension
) : PlatformTarget(objects, kotlinExtension, "commonMain", "commonTest")

open class AndroidTarget @Inject constructor(
    objects: ObjectFactory,
    kotlinExtension: KotlinMultiplatformExtension
) : PlatformTarget(objects, kotlinExtension, "androidMain", "androidUnitTest")

open class IosTarget @Inject constructor(
    objects: ObjectFactory,
    kotlinExtension: KotlinMultiplatformExtension
) : PlatformTarget(objects, kotlinExtension, "iosMain", "iosTest")

open class DesktopTarget @Inject constructor(
    objects: ObjectFactory,
    kotlinExtension: KotlinMultiplatformExtension
) : PlatformTarget(objects, kotlinExtension, "desktopMain", "desktopTest")

open class SourceSetConfiguration @Inject constructor(
    private val kotlinExtension: KotlinMultiplatformExtension,
    private val sourceSetName: String
) {
    private val sourceSet: KotlinSourceSet
        get() = kotlinExtension.sourceSets.getByName(sourceSetName)

    val dependencies: DependenciesConfiguration
        get() = DependenciesConfiguration(sourceSet)

    fun dependencies(action: Action<DependenciesConfiguration>) {
        action.execute(dependencies)
    }
}

open class DependenciesConfiguration(
    private val sourceSet: KotlinSourceSet
) {
    fun implementation(dependencyNotation: Any) = sourceSet.dependencies { implementation(dependencyNotation) }

    fun api(dependencyNotation: Any) = sourceSet.dependencies { api(dependencyNotation) }

    fun compileOnly(dependencyNotation: Any) = sourceSet.dependencies { compileOnly(dependencyNotation) }

    fun runtimeOnly(dependencyNotation: Any) = sourceSet.dependencies { runtimeOnly(dependencyNotation) }
}

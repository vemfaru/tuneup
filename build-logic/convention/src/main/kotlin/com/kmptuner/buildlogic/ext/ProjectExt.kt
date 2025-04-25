package com.kmptuner.buildlogic.ext

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.findByType
import org.gradle.kotlin.dsl.getByType

/**
 * Retrieves the common extension applicable to the project, either for an application or a library.
 *
 * @throws IllegalStateException If neither an application nor a library plugin has been applied.
 * @return The applicable common extension.
 * @see ApplicationExtension
 * @see LibraryExtension
 * @see CommonExtension
 */
internal val Project.commonExtension: CommonExtension<*, *, *, *, *, *>?
    get() = extensions.findByType<ApplicationExtension>()
        ?: extensions.findByType<LibraryExtension>()

/**
 * Checks if the project is a Kotlin Multiplatform project.
 */
internal val Project.isMultiplatform: Boolean
    get() = plugins.hasPlugin("kmptuner.multiplatform")

/**
 * Adds a debug dependency to the project using a provider for the dependency.
 */
internal fun DependencyHandler.debugImplementation(provider: Provider<MinimalExternalModuleDependency>) {
    addProvider("debugImplementation", provider)
}

/**
 * Adds a debug dependency to the project using a provider for the dependency.
 */
internal fun DependencyHandler.kmpAwareImplementation(
    isMultiplatform: Boolean,
    provider: Provider<MinimalExternalModuleDependency>
) {
    val implementConfiguration = when {
        isMultiplatform -> "androidMainImplementation"
        else -> "implementation"
    }
    addProvider(implementConfiguration, provider)
}
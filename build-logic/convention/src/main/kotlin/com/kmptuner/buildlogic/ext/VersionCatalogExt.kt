package com.kmptuner.buildlogic.ext

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType


/**
 * Provides access to the version catalog named "libs".
 *
 * Due to composite builds, there's no direct access to the generated libs accessor.
 * Thus, this extension property provides a way to access the named version catalog "libs".
 *
 * @receiver [Project] The Gradle project on which the property is accessed.
 * @return The version catalog named "libs".
 */
internal val Project.libs: VersionCatalog
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

internal val VersionCatalog.composeUiTooling
    get() = findLibrary("compose-ui-tooling").get()

internal val VersionCatalog.composePreview
    get() = findLibrary("compose-ui-tooling-preview").get()

internal val VersionCatalog.composeCompilerPluginId
    get() = findPlugin("compose-compiler").get().get().pluginId
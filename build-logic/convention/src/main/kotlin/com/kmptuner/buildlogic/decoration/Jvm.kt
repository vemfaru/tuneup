package com.kmptuner.buildlogic.decoration

import com.kmptuner.buildlogic.options.CompilationOptions
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget.Companion.fromTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/**
 * Applies the Java Library plugin to the project and sets the necessary configurations.
 *
 * This function applies the "java-library" plugin and configures the Java compatibility and Kotlin options
 * based on the provided [compilationOptions].
 *
 * @param compilationOptions The compilation options to apply to the project.
 * @receiver [Project] The Gradle project on which the function is applied.
 */
internal fun Project.applyJvmLibrary(compilationOptions: CompilationOptions) {
    pluginManager.apply("java-library")
    applyJavaCompatibility(compilationOptions.javaVersion)
    applyKotlinOptions(compilationOptions)
}

/**
 * Sets the source and target Java compatibility for the project.
 *
 * This function configures the Java compatibility for both source and target using the provided [javaVersion].
 *
 * @param javaVersion The Java version to set for source and target compatibility.
 * @receiver [Project] The Gradle project on which the function is applied.
 */
private fun Project.applyJavaCompatibility(javaVersion: JavaVersion) {
    extensions.configure<JavaPluginExtension> {
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }
}

/**
 * Configures Kotlin-related options for the project's compilation tasks.
 *
 * This function sets various Kotlin options, such as treating warnings as errors, specifying the JVM target version,
 * and adding extra compiler arguments based on the provided [compilationOptions].
 *
 * @param compilationOptions The compilation options to apply to the Kotlin tasks.
 * @receiver [Project] The Gradle project on which the function is applied.
 */
internal fun Project.applyKotlinOptions(compilationOptions: CompilationOptions) {
    tasks.withType<KotlinCompile>().configureEach {
        compilerOptions {
            allWarningsAsErrors.set(compilationOptions.allWarningsAsErrors)
            jvmTarget.set(fromTarget(compilationOptions.jvmTarget))
            freeCompilerArgs.addAll(compilationOptions.extraFreeCompilerArgs)
        }
    }
}

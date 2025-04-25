package com.kmptuner.buildlogic.decoration

import com.android.build.api.dsl.ApplicationBuildType
import com.android.build.api.dsl.ApplicationDefaultConfig
import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.DefaultConfig
import com.android.build.api.dsl.LibraryBuildType
import com.android.build.api.dsl.LibraryDefaultConfig
import com.android.build.api.dsl.LibraryExtension
import com.kmptuner.buildlogic.ext.commonExtension
import com.kmptuner.buildlogic.options.android.AndroidBuildType
import com.kmptuner.buildlogic.options.android.AndroidOptions
import com.kmptuner.buildlogic.options.CompilationOptions
import com.kmptuner.buildlogic.options.android.AndroidProguardOptions
import com.kmptuner.buildlogic.options.android.AndroidAppOptions
import com.kmptuner.buildlogic.options.android.AndroidBuildType.DebugBuildType
import com.kmptuner.buildlogic.options.android.AndroidBuildType.ReleaseBuildType
import com.kmptuner.buildlogic.options.android.AndroidLibraryOptions
import com.kmptuner.buildlogic.options.requireOptions
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

/**
 * Applies common configurations tailored for Android applications.
 *
 * @param androidAppOptions Specifies the Android application-specific options.
 * @param compilationOptions Specifies the compilation options.
 *
 * @see AndroidAppOptions
 * @see CompilationOptions
 */
internal fun Project.applyAndroidApp(
    androidAppOptions: AndroidAppOptions = requireOptions(),
    compilationOptions: CompilationOptions = requireOptions(),
) {
    pluginManager.apply("com.android.application")

    applyAndroidCommon(
        androidOptions = androidAppOptions,
        compilationOptions = compilationOptions
    )

    extensions.configure<ApplicationExtension> {
        defaultConfig {
            applicationId = androidAppOptions.applicationId
            targetSdk = androidAppOptions.targetSdk
            versionCode = androidAppOptions.versionCode
            versionName = androidAppOptions.versionName

            val config: ApplicationDefaultConfig = this
            setProguardFiles(
                config = config,
                androidProguardOptions = androidAppOptions.androidProguardOptions,
                consume = { proguardFiles(*it) }
            )
        }

        buildFeatures {
            buildConfig = androidAppOptions.androidBuildFeatures.generateBuildConfig
        }

        setAppBuildTypes(androidAppOptions)
    }
}

/**
 * Applies common configurations tailored for Android libraries.
 *
 * @param androidLibraryOptions Specifies the Android library-specific options.
 * @param compilationOptions Specifies the compilation options.
 *
 * @see AndroidLibraryOptions
 * @see CompilationOptions
 */
internal fun Project.applyAndroidLibrary(
    androidLibraryOptions: AndroidLibraryOptions = requireOptions(),
    compilationOptions: CompilationOptions = requireOptions(),
) {
    pluginManager.apply("com.android.library")
    logger.info("Android library options: $androidLibraryOptions")
    applyAndroidCommon(
        androidOptions = androidLibraryOptions,
        compilationOptions = compilationOptions
    )

    extensions.configure<LibraryExtension> {
        defaultConfig {
            val config: LibraryDefaultConfig = this
            setProguardFiles(
                config = config,
                androidProguardOptions = androidLibraryOptions.androidProguardOptions,
                consume = { consumerProguardFiles(*it) }
            )
        }

        setLibraryBuildTypes(androidLibraryOptions)

        buildFeatures {
            androidResources = androidLibraryOptions.androidBuildFeatures.generateAndroidResources
            resValues = androidLibraryOptions.androidBuildFeatures.generateResValues
            buildConfig = androidLibraryOptions.androidBuildFeatures.generateBuildConfig
        }
    }
}

/**
 * Configures the shared Android settings that are common between applications and libraries.
 *
 * @param androidOptions Contains generic Android settings.
 * @param compilationOptions Specifies the compilation options.
 *
 * @see AndroidOptions
 * @see CompilationOptions
 */
private fun Project.applyAndroidCommon(
    androidOptions: AndroidOptions,
    compilationOptions: CompilationOptions
) = with(commonExtension!!) {
    //todo: patternize the namespace generation based on project path
    namespace = androidOptions.namespace
    compileSdk = androidOptions.compileSdk

    defaultConfig {
        minSdk = androidOptions.minSdk

        vectorDrawables {
            useSupportLibrary = androidOptions.useVectorDrawables
        }
    }

    compileOptions {
        sourceCompatibility = androidOptions.javaVersion
        targetCompatibility = androidOptions.javaVersion
    }

    applyKotlinOptions(compilationOptions)

    packaging {
        resources {
            excludes += androidOptions.androidPackagingOptions.excludes
        }
    }
}

/**
 * Sets build types for the Android application based on provided options.
 *
 * @param options Contains configurations related to Android app's build types.
 * @see AndroidAppOptions
 */
private fun ApplicationExtension.setAppBuildTypes(options: AndroidAppOptions) {
    fun ApplicationBuildType.applyFrom(androidBuildType: AndroidBuildType) {
        isDebuggable = androidBuildType.isDebuggable
        isMinifyEnabled = androidBuildType.isMinifyEnabled
        isShrinkResources = androidBuildType.shrinkResources
        multiDexEnabled = androidBuildType.multidex
        versionNameSuffix = androidBuildType.versionNameSuffix
    }

    buildTypes {
        options.buildTypes.forEach { androidBuildType ->
            when (androidBuildType) {
                DebugBuildType -> debug { applyFrom(androidBuildType) }
                ReleaseBuildType -> release { applyFrom(androidBuildType) }
                else -> create(androidBuildType.name) { applyFrom(androidBuildType) }
            }
        }
    }
}

/**
 * Sets build types for the Android library based on provided options.
 *
 * @param options Contains configurations related to Android library's build types.
 * @see AndroidLibraryOptions
 */
private fun LibraryExtension.setLibraryBuildTypes(options: AndroidLibraryOptions) {
    fun LibraryBuildType.applyFrom(androidBuildType: AndroidBuildType) {
        isMinifyEnabled = androidBuildType.isMinifyEnabled
        multiDexEnabled = androidBuildType.multidex
    }

    buildTypes {
        options.buildTypes.forEach { androidBuildType ->
            when (androidBuildType) {
                DebugBuildType -> debug { applyFrom(androidBuildType) }
                ReleaseBuildType -> release { applyFrom(androidBuildType) }
                else -> create(androidBuildType.name) { applyFrom(androidBuildType) }
            }
        }
    }
}

/**
 * Configures Proguard files based on the provided options.
 *
 * @param config Configuration object to apply the Proguard settings to.
 * @param androidProguardOptions Contains Proguard configurations.
 * @param consume Lambda to consume the resultant Proguard file paths.
 *
 * @see AndroidProguardOptions
 */
private fun <T : DefaultConfig> Project.setProguardFiles(
    config: T,
    androidProguardOptions: AndroidProguardOptions,
    consume: T.(Array<Any>) -> Unit
) {
    config.consume(arrayOf(androidProguardOptions.fileName))
//    if (androidProguardOptions.applyWithOptimizedVersion) {
//        config.consume(
//            arrayOf(
//                getDefaultProguardFile("proguard-android-optimize.txt", layout.buildDirectory),
//                androidProguardOptions.fileName
//            )
//        )
//    } else {
//        config.consume(arrayOf(androidProguardOptions.fileName))
//    }
}
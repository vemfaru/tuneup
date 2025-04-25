package com.kmptuner.buildlogic.options.android

import org.gradle.api.JavaVersion

/**
 * Aliases for constructing Android application and library options.
 */
typealias AndroidAppBuilder = AndroidAppOptions.Builder.() -> Unit
typealias AndroidLibraryBuilder = AndroidLibraryOptions.Builder.() -> Unit

/**
 * Represents a set of common options to be applied to an Android project.
 *
 * These types are delegated to the Android Gradle Plugin (AGP) and are used to configure
 * various aspects of the Android build process.
 *
 * Both the App and Library versions of AGP follows similar patterns, but they have
 * different configurations.
 *
 * @see AndroidAppOptions
 * @see AndroidLibraryOptions
 *
 * @property namespace The project's package namespace.
 * @property compileSdk The compile SDK version.
 * @property minSdk The minimum SDK version.
 * @property useVectorDrawables Flag indicating whether vector drawables are used.
 * @property javaVersion The Java version.
 * @property androidPackagingOptions Configuration for packaging options.
 * @property androidProguardOptions Configuration for Proguard.
 * @property buildTypes List of build types for the project (e.g., debug, release).
 * @property androidBuildFeatures Configuration for Android build features.
 * @property targetSdk The target SDK version.
 */
sealed class AndroidOptions(
    open val namespace: String,
    open val targetSdk: Int,
    open val compileSdk: Int,
    open val minSdk: Int,
    open val androidBuildFeatures: AndroidBuildFeatures,
    open val useVectorDrawables: Boolean,
    open val javaVersion: JavaVersion,
    open val androidPackagingOptions: AndroidPackagingOptions,
    open val androidProguardOptions: AndroidProguardOptions,
    open val buildTypes: List<AndroidBuildType>
) {
    /**
     * Base class to construct Android options.
     *
     * Provides a foundation for creating Android configurations with default values.
     */
    abstract class Builder {

        var namespace: String = "com.kmp.tuner"
        var targetSdk: Int = 36
        var compileSdk: Int = 36
        var minSdk: Int = 24
        var useVectorDrawables: Boolean = true
        var javaVersion: JavaVersion = JavaVersion.VERSION_17
        var buildTypes: List<AndroidBuildType> = AndroidBuildType.defaultBuildTypes
        internal var androidPackagingOptions: AndroidPackagingOptions = AndroidPackagingOptions()
        internal var androidBuildFeatures = AndroidBuildFeaturesBuilder()
        internal open var androidProguardOptionsBuilder = AndroidProguardOptions.Builder("proguard-rules.pro")

        fun proguardOptions(init: AndroidProguardOptions.Builder.() -> Unit) {
            androidProguardOptionsBuilder.apply(init)
        }

        fun buildFeatures(block: AndroidBuildFeaturesBuilder.() -> Unit) {
            androidBuildFeatures.apply(block)
        }

        internal abstract fun build(): AndroidOptions
    }
}

/**
 * Represents the options specific to an Android application.
 *
 * @property applicationId The application's ID.
 * @property versionCode The version code for the application.
 * @property versionName The version name for the application.
 */
data class AndroidAppOptions(
    val applicationId: String,
    val versionCode: Int,
    val versionName: String,
    override val androidProguardOptions: AndroidProguardOptions,
    override val namespace: String,
    override val targetSdk: Int,
    override val compileSdk: Int,
    override val minSdk: Int,
    override val androidBuildFeatures: AndroidBuildFeatures,
    override val useVectorDrawables: Boolean,
    override val javaVersion: JavaVersion,
    override val androidPackagingOptions: AndroidPackagingOptions,
    override val buildTypes: List<AndroidBuildType>
) : AndroidOptions(
    namespace = namespace,
    compileSdk = compileSdk,
    minSdk = minSdk,
    useVectorDrawables = useVectorDrawables,
    javaVersion = javaVersion,
    androidPackagingOptions = androidPackagingOptions,
    androidProguardOptions = androidProguardOptions,
    buildTypes = buildTypes,
    androidBuildFeatures = androidBuildFeatures,
    targetSdk = targetSdk
) {

    /**
     * Builder class to construct options specific to an Android application.
     */
    class Builder : AndroidOptions.Builder() {

        var applicationId: String = namespace
        var versionCode: Int = 1
        var versionName: String = "1.0"
        override var androidProguardOptionsBuilder = AndroidProguardOptions.Builder("proguard-rules.pro")

        override fun build() = AndroidAppOptions(
            applicationId = applicationId,
            targetSdk = targetSdk,
            versionCode = versionCode,
            versionName = versionName,
            androidProguardOptions = androidProguardOptionsBuilder.build(),
            namespace = namespace,
            compileSdk = compileSdk,
            minSdk = minSdk,
            useVectorDrawables = useVectorDrawables,
            javaVersion = javaVersion,
            androidPackagingOptions = androidPackagingOptions,
            buildTypes = buildTypes,
            androidBuildFeatures = androidBuildFeatures.build()
        )
    }
}

/**
 * Represents the options specific to an Android library.
 */
data class AndroidLibraryOptions(
    override val androidBuildFeatures: AndroidBuildFeatures,
    override val androidProguardOptions: AndroidProguardOptions,
    override val namespace: String,
    override val targetSdk: Int,
    override val compileSdk: Int,
    override val minSdk: Int,
    override val useVectorDrawables: Boolean,
    override val javaVersion: JavaVersion,
    override val androidPackagingOptions: AndroidPackagingOptions,
    override val buildTypes: List<AndroidBuildType>
) : AndroidOptions(
    namespace = namespace,
    compileSdk = compileSdk,
    minSdk = minSdk,
    useVectorDrawables = useVectorDrawables,
    javaVersion = javaVersion,
    androidPackagingOptions = androidPackagingOptions,
    androidProguardOptions = androidProguardOptions,
    buildTypes = buildTypes,
    androidBuildFeatures = androidBuildFeatures,
    targetSdk = targetSdk
) {
    /**
     * Builder class to construct options specific to an Android library.
     */
    class Builder : AndroidOptions.Builder() {
        override var androidProguardOptionsBuilder = AndroidProguardOptions.Builder("consumer-proguard-rules.pro")

        override fun build() = AndroidLibraryOptions(
            androidProguardOptions = androidProguardOptionsBuilder.build(),
            namespace = namespace,
            compileSdk = compileSdk,
            minSdk = minSdk,
            useVectorDrawables = useVectorDrawables,
            javaVersion = javaVersion,
            buildTypes = buildTypes,
            androidPackagingOptions = androidPackagingOptions,
            androidBuildFeatures = androidBuildFeatures.build(),
            targetSdk = targetSdk
        )
    }
}
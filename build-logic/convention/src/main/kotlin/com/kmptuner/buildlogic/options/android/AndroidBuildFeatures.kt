package com.kmptuner.buildlogic.options.android

internal typealias AndroidBuildFeaturesBuilder = AndroidBuildFeatures.Builder

/**
 * Represents build-specific features for an Android library.
 *
 * @property generateAndroidResources Flag indicating whether Android resources are generated.
 * @property generateResValues Flag indicating whether resource values are generated.
 * @property generateBuildConfig Flag indicating whether a BuildConfig file is generated.
 */
data class AndroidBuildFeatures(
    val generateAndroidResources: Boolean,
    val generateResValues: Boolean,
    val generateBuildConfig: Boolean,
) {
    class Builder {

        var generateAndroidResources: Boolean = false
        var generateResValues: Boolean = false
        var generateBuildConfig: Boolean = false

        internal fun build() = AndroidBuildFeatures(
            generateAndroidResources = generateAndroidResources,
            generateResValues = generateResValues,
            generateBuildConfig = generateBuildConfig
        )
    }
}
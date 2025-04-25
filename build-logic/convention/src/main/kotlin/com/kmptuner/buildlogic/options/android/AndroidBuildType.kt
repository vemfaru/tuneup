package com.kmptuner.buildlogic.options.android

interface AndroidBuildType {

    val name: String
    val isMinifyEnabled: Boolean
    val shrinkResources: Boolean
    val versionNameSuffix: String?
    val isDebuggable: Boolean
    val multidex: Boolean

    object ReleaseBuildType : AndroidBuildType {

        override val name: String = "release"
        override val isMinifyEnabled: Boolean = true
        override val shrinkResources: Boolean = true
        override val versionNameSuffix: String? = null
        override val isDebuggable: Boolean = false
        override val multidex: Boolean = false
    }

    object DebugBuildType : AndroidBuildType {

        override val name: String = "debug"
        override val isMinifyEnabled: Boolean = false
        override val shrinkResources: Boolean = false
        override val versionNameSuffix: String = "debug"
        override val isDebuggable: Boolean = true
        override val multidex: Boolean = false
    }

    companion object {
        internal val defaultBuildTypes: List<AndroidBuildType> = listOf(ReleaseBuildType, DebugBuildType)
    }
}
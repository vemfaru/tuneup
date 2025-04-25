package com.kmptuner.buildlogic.options.android

data class AndroidProguardOptions(
    val fileName: String,
    val applyWithOptimizedVersion: Boolean = true
) {

    class Builder(defaultFileName: String) {

        var fileName: String = defaultFileName
        var applyWithOptimizedVersion: Boolean = true

        internal fun build() = AndroidProguardOptions(
            fileName = fileName,
            applyWithOptimizedVersion = applyWithOptimizedVersion
        )
    }
}
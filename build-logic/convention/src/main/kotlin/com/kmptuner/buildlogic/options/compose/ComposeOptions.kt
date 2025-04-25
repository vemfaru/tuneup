package com.kmptuner.buildlogic.options.compose

/**
 * Alias for constructing compose options.
 */
typealias ComposeOptionsBuilder = ComposeOptions.Builder.() -> Unit

/**
 * Represents a set of options for configuring Compose Multiplatform.
 *
 * @property enableTooling Flag indicating if Compose tooling (preview, etc.) should be enabled.
 */
data class ComposeOptions(
    val enableTooling: Boolean
) {

    /**
     * Builder class to construct [ComposeOptions].
     *
     * Offers a fluent API to configure various Compose settings.
     */
    class Builder {
        var enableTooling: Boolean = false

        internal fun build(): ComposeOptions = ComposeOptions(
            enableTooling = enableTooling
        )
    }
}
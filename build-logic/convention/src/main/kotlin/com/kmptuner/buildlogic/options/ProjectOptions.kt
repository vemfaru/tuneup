package com.kmptuner.buildlogic.options

import org.gradle.api.Project
import org.gradle.kotlin.dsl.extra

const val PROJECT_OPTIONS_KEY = "com.kmp.tuner.gradle.plugins"

/**
 * Retrieve a reified object from Project's extra.
 *
 * If null/not present, an exception will be thrown.
 *
 * Inspired by https://github.com/arkivanov/gradle-setup-plugin/blob/master/src/main/java/com/arkivanov/gradle/SetupDefaults.kt
 * @param key: default is [PROJECT_OPTIONS_KEY]
 * */
internal inline fun <reified T : Any> Project.requireOptions(key: String = PROJECT_OPTIONS_KEY): T =
    requireNotNull(getOption(key)) { "Defaults not found for type ${T::class}" }

internal inline fun <reified T : Any> Project.getOption(key: String): T? =
    getOption(key) { it as? T }

internal fun <T : Any> Project.getOption(key: String, mapper: (Any) -> T?): T? =
    getOptionList(key)?.asSequence()?.mapNotNull(mapper)?.firstOrNull()
        ?: parent?.getOption(key, mapper)

@Suppress("UNCHECKED_CAST")
private fun Project.getOptionList(key: String): MutableList<Any>? =
    extra.takeIf { it.has(key) }?.get(key) as ArrayList<Any>?
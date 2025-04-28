import com.kmptuner.buildlogic.options.CompilationOptions
import com.kmptuner.buildlogic.options.CompilationOptionsBuilder
import com.kmptuner.buildlogic.options.PROJECT_OPTIONS_KEY
import com.kmptuner.buildlogic.options.android.AndroidAppBuilder
import com.kmptuner.buildlogic.options.android.AndroidAppOptions
import com.kmptuner.buildlogic.options.android.AndroidLibraryBuilder
import com.kmptuner.buildlogic.options.android.AndroidLibraryOptions
import org.gradle.api.Project
import org.gradle.internal.extensions.core.extra

/**
 * This file is meant to be the entry point for the build-logic features.
 * Declaring the main entry points on this file, avoids imports inside build.gradle.kts files.
 *
 * This is specially useful for allowing moving packages around without breaking imports.
 *
 * Also, it allows a cleaner syntax for the build.gradle.kts files, reducing the need for
 * imports.
 * */

fun Project.installDefaults(
    androidAppBuilder: AndroidAppBuilder = { build() },
    androidLibraryBuilder: AndroidLibraryBuilder = { build() },
    compilationBuilder: CompilationOptionsBuilder = { build() }
) {
    val androidAppOptions = AndroidAppOptions.Builder()
        .apply(androidAppBuilder)
        .build()

    val androidLibraryOptions = AndroidLibraryOptions.Builder()
        .apply(androidLibraryBuilder)
        .build()

    val compilationOptions = CompilationOptions.Builder()
        .apply(compilationBuilder)
        .build()

    extra.set(
        /* name = */ PROJECT_OPTIONS_KEY,
        /* value = */ listOfNotNull(
            androidAppOptions,
            androidLibraryOptions,
            compilationOptions
        )
    )
}
package com.kmptuner.core.design.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import com.kmptuner.core.design.color.darkColorScheme
import com.kmptuner.core.design.color.lightColorScheme
import com.kmptuner.core.design.shape.AppShapes
import com.kmptuner.core.design.typography.AppTypography

/**
 * Custom theme tokens that are not part of Material3
 */
data class AppThemeTokens(
    val isDarkTheme: Boolean
)

/**
 * CompositionLocal used to access the custom theme tokens
 */
val LocalAppThemeTokens = staticCompositionLocalOf {
    AppThemeTokens(isDarkTheme = false)
}

/**
 * Extension to access the custom theme tokens from MaterialTheme
 */
val MaterialTheme.appThemeTokens: AppThemeTokens
    @Composable
    @ReadOnlyComposable
    get() = LocalAppThemeTokens.current

/**
 * App theme composable that applies Material3 theme with our custom colors, typography, and shapes
 *
 * @param darkTheme Whether to use dark theme. Defaults to system setting.
 * @param content The content to be themed
 */
@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) darkColorScheme() else lightColorScheme()
    
    val appThemeTokens = AppThemeTokens(
        isDarkTheme = darkTheme
    )
    
    CompositionLocalProvider(
        LocalAppThemeTokens provides appThemeTokens
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = AppTypography,
            shapes = AppShapes,
            content = content
        )
    }
}

/**
 * A convenience wrapper around [AppTheme] that also applies a [Surface] with the theme's background color
 *
 * @param darkTheme Whether to use dark theme. Defaults to system setting.
 * @param content The content to be themed
 */
@Composable
fun AppThemeWithBackground(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    AppTheme(darkTheme = darkTheme) {
        Surface(
            color = MaterialTheme.colorScheme.background,
            content = content
        )
    }
}
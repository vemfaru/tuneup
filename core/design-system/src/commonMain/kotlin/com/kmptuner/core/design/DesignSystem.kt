package com.kmptuner.core.design

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import com.kmptuner.core.design.color.AppColors
import com.kmptuner.core.design.color.darkColorScheme
import com.kmptuner.core.design.color.lightColorScheme
import com.kmptuner.core.design.components.AppButton
import com.kmptuner.core.design.shape.AppShapes
import com.kmptuner.core.design.theme.AppTheme
import com.kmptuner.core.design.theme.AppThemeTokens
import com.kmptuner.core.design.theme.AppThemeWithBackground
import com.kmptuner.core.design.theme.appThemeTokens
import com.kmptuner.core.design.typography.AppTypography

/**
 * Public API for the design system
 *
 * This file re-exports all the public components of the design system to provide a clean API.
 */

// Theme
@Composable
fun KmpTunerTheme(
    darkTheme: Boolean,
    content: @Composable () -> Unit
) = AppTheme(darkTheme = darkTheme, content = content)

@Composable
fun KmpTunerThemeWithBackground(
    darkTheme: Boolean,
    content: @Composable () -> Unit
) = AppThemeWithBackground(darkTheme = darkTheme, content = content)

// Theme tokens
val MaterialTheme.kmpTunerTokens: AppThemeTokens
    @Composable
    get() = appThemeTokens

// Colors
object KmpTunerColors {
    val light: ColorScheme = lightColorScheme()
    val dark: ColorScheme = darkColorScheme()

    // Re-export all colors from AppColors
    val primary = AppColors.primary
    val onPrimary = AppColors.onPrimary
    val primaryContainer = AppColors.primaryContainer
    val onPrimaryContainer = AppColors.onPrimaryContainer

    val secondary = AppColors.secondary
    val onSecondary = AppColors.onSecondary
    val secondaryContainer = AppColors.secondaryContainer
    val onSecondaryContainer = AppColors.onSecondaryContainer

    val tertiary = AppColors.tertiary
    val onTertiary = AppColors.onTertiary
    val tertiaryContainer = AppColors.tertiaryContainer
    val onTertiaryContainer = AppColors.onTertiaryContainer

    val background = AppColors.background
    val onBackground = AppColors.onBackground

    val surface = AppColors.surface
    val onSurface = AppColors.onSurface
    val surfaceVariant = AppColors.surfaceVariant
    val onSurfaceVariant = AppColors.onSurfaceVariant

    val error = AppColors.error
    val onError = AppColors.onError
    val errorContainer = AppColors.errorContainer
    val onErrorContainer = AppColors.onErrorContainer
}

// Typography
val KmpTunerTypography: Typography = AppTypography

// Shapes
val KmpTunerShapes: Shapes = AppShapes

// Components
@Composable
fun KmpTunerButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit
) = AppButton(
    onClick = onClick,
    modifier = modifier,
    enabled = enabled,
    contentPadding = contentPadding,
    content = content
)

@Composable
fun KmpTunerButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) = AppButton(
    text = text,
    onClick = onClick,
    modifier = modifier,
    enabled = enabled
)

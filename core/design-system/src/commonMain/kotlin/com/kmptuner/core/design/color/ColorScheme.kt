package com.kmptuner.core.design.color

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

/**
 * App color palette
 */
object AppColors {
    // Primary colors
    val primary = Color(0xFF6750A4)
    val onPrimary = Color(0xFFFFFFFF)
    val primaryContainer = Color(0xFFEADDFF)
    val onPrimaryContainer = Color(0xFF21005D)
    
    // Secondary colors
    val secondary = Color(0xFF625B71)
    val onSecondary = Color(0xFFFFFFFF)
    val secondaryContainer = Color(0xFFE8DEF8)
    val onSecondaryContainer = Color(0xFF1D192B)
    
    // Tertiary colors
    val tertiary = Color(0xFF7D5260)
    val onTertiary = Color(0xFFFFFFFF)
    val tertiaryContainer = Color(0xFFFFD8E4)
    val onTertiaryContainer = Color(0xFF31111D)
    
    // Background colors
    val background = Color(0xFFFFFBFE)
    val onBackground = Color(0xFF1C1B1F)
    
    // Surface colors
    val surface = Color(0xFFFFFBFE)
    val onSurface = Color(0xFF1C1B1F)
    val surfaceVariant = Color(0xFFE7E0EC)
    val onSurfaceVariant = Color(0xFF49454F)
    
    // Error colors
    val error = Color(0xFFB3261E)
    val onError = Color(0xFFFFFFFF)
    val errorContainer = Color(0xFFF9DEDC)
    val onErrorContainer = Color(0xFF410E0B)
    
    // Dark theme colors
    val primaryDark = Color(0xFFD0BCFF)
    val onPrimaryDark = Color(0xFF381E72)
    val primaryContainerDark = Color(0xFF4F378B)
    val onPrimaryContainerDark = Color(0xFFEADDFF)
    
    val secondaryDark = Color(0xFFCCC2DC)
    val onSecondaryDark = Color(0xFF332D41)
    val secondaryContainerDark = Color(0xFF4A4458)
    val onSecondaryContainerDark = Color(0xFFE8DEF8)
    
    val tertiaryDark = Color(0xFFEFB8C8)
    val onTertiaryDark = Color(0xFF492532)
    val tertiaryContainerDark = Color(0xFF633B48)
    val onTertiaryContainerDark = Color(0xFFFFD8E4)
    
    val backgroundDark = Color(0xFF1C1B1F)
    val onBackgroundDark = Color(0xFFE6E1E5)
    
    val surfaceDark = Color(0xFF1C1B1F)
    val onSurfaceDark = Color(0xFFE6E1E5)
    val surfaceVariantDark = Color(0xFF49454F)
    val onSurfaceVariantDark = Color(0xFFCAC4D0)
    
    val errorDark = Color(0xFFF2B8B5)
    val onErrorDark = Color(0xFF601410)
    val errorContainerDark = Color(0xFF8C1D18)
    val onErrorContainerDark = Color(0xFFF9DEDC)
}

/**
 * Returns the light color scheme for the app
 */
fun lightColorScheme(): ColorScheme = lightColorScheme(
    primary = AppColors.primary,
    onPrimary = AppColors.onPrimary,
    primaryContainer = AppColors.primaryContainer,
    onPrimaryContainer = AppColors.onPrimaryContainer,
    
    secondary = AppColors.secondary,
    onSecondary = AppColors.onSecondary,
    secondaryContainer = AppColors.secondaryContainer,
    onSecondaryContainer = AppColors.onSecondaryContainer,
    
    tertiary = AppColors.tertiary,
    onTertiary = AppColors.onTertiary,
    tertiaryContainer = AppColors.tertiaryContainer,
    onTertiaryContainer = AppColors.onTertiaryContainer,
    
    background = AppColors.background,
    onBackground = AppColors.onBackground,
    
    surface = AppColors.surface,
    onSurface = AppColors.onSurface,
    surfaceVariant = AppColors.surfaceVariant,
    onSurfaceVariant = AppColors.onSurfaceVariant,
    
    error = AppColors.error,
    onError = AppColors.onError,
    errorContainer = AppColors.errorContainer,
    onErrorContainer = AppColors.onErrorContainer
)

/**
 * Returns the dark color scheme for the app
 */
fun darkColorScheme(): ColorScheme = darkColorScheme(
    primary = AppColors.primaryDark,
    onPrimary = AppColors.onPrimaryDark,
    primaryContainer = AppColors.primaryContainerDark,
    onPrimaryContainer = AppColors.onPrimaryContainerDark,
    
    secondary = AppColors.secondaryDark,
    onSecondary = AppColors.onSecondaryDark,
    secondaryContainer = AppColors.secondaryContainerDark,
    onSecondaryContainer = AppColors.onSecondaryContainerDark,
    
    tertiary = AppColors.tertiaryDark,
    onTertiary = AppColors.onTertiaryDark,
    tertiaryContainer = AppColors.tertiaryContainerDark,
    onTertiaryContainer = AppColors.onTertiaryContainerDark,
    
    background = AppColors.backgroundDark,
    onBackground = AppColors.onBackgroundDark,
    
    surface = AppColors.surfaceDark,
    onSurface = AppColors.onSurfaceDark,
    surfaceVariant = AppColors.surfaceVariantDark,
    onSurfaceVariant = AppColors.onSurfaceVariantDark,
    
    error = AppColors.errorDark,
    onError = AppColors.onErrorDark,
    errorContainer = AppColors.errorContainerDark,
    onErrorContainer = AppColors.onErrorContainerDark
)
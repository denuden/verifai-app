package com.example.compose
import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.gmail.denuelle42.aiprompter.ui.theme.AppTypography
import com.gmail.denuelle42.aiprompter.ui.theme.backgroundDark
import com.gmail.denuelle42.aiprompter.ui.theme.backgroundDarkHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.backgroundDarkMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.backgroundLight
import com.gmail.denuelle42.aiprompter.ui.theme.backgroundLightHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.backgroundLightMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.errorContainerDark
import com.gmail.denuelle42.aiprompter.ui.theme.errorContainerDarkHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.errorContainerDarkMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.errorContainerLight
import com.gmail.denuelle42.aiprompter.ui.theme.errorContainerLightHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.errorContainerLightMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.errorDark
import com.gmail.denuelle42.aiprompter.ui.theme.errorDarkHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.errorDarkMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.errorLight
import com.gmail.denuelle42.aiprompter.ui.theme.errorLightHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.errorLightMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.inverseOnSurfaceDark
import com.gmail.denuelle42.aiprompter.ui.theme.inverseOnSurfaceDarkHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.inverseOnSurfaceDarkMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.inverseOnSurfaceLight
import com.gmail.denuelle42.aiprompter.ui.theme.inverseOnSurfaceLightHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.inverseOnSurfaceLightMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.inversePrimaryDark
import com.gmail.denuelle42.aiprompter.ui.theme.inversePrimaryDarkHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.inversePrimaryDarkMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.inversePrimaryLight
import com.gmail.denuelle42.aiprompter.ui.theme.inversePrimaryLightHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.inversePrimaryLightMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.inverseSurfaceDark
import com.gmail.denuelle42.aiprompter.ui.theme.inverseSurfaceDarkHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.inverseSurfaceDarkMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.inverseSurfaceLight
import com.gmail.denuelle42.aiprompter.ui.theme.inverseSurfaceLightHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.inverseSurfaceLightMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.onBackgroundDark
import com.gmail.denuelle42.aiprompter.ui.theme.onBackgroundDarkHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.onBackgroundDarkMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.onBackgroundLight
import com.gmail.denuelle42.aiprompter.ui.theme.onBackgroundLightHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.onBackgroundLightMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.onErrorContainerDark
import com.gmail.denuelle42.aiprompter.ui.theme.onErrorContainerDarkHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.onErrorContainerDarkMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.onErrorContainerLight
import com.gmail.denuelle42.aiprompter.ui.theme.onErrorContainerLightHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.onErrorContainerLightMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.onErrorDark
import com.gmail.denuelle42.aiprompter.ui.theme.onErrorDarkHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.onErrorDarkMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.onErrorLight
import com.gmail.denuelle42.aiprompter.ui.theme.onErrorLightHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.onErrorLightMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.onPrimaryContainerDark
import com.gmail.denuelle42.aiprompter.ui.theme.onPrimaryContainerDarkHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.onPrimaryContainerDarkMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.onPrimaryContainerLight
import com.gmail.denuelle42.aiprompter.ui.theme.onPrimaryContainerLightHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.onPrimaryContainerLightMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.onPrimaryDark
import com.gmail.denuelle42.aiprompter.ui.theme.onPrimaryDarkHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.onPrimaryDarkMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.onPrimaryLight
import com.gmail.denuelle42.aiprompter.ui.theme.onPrimaryLightHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.onPrimaryLightMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.onSecondaryContainerDark
import com.gmail.denuelle42.aiprompter.ui.theme.onSecondaryContainerDarkHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.onSecondaryContainerDarkMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.onSecondaryContainerLight
import com.gmail.denuelle42.aiprompter.ui.theme.onSecondaryContainerLightHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.onSecondaryContainerLightMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.onSecondaryDark
import com.gmail.denuelle42.aiprompter.ui.theme.onSecondaryDarkHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.onSecondaryDarkMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.onSecondaryLight
import com.gmail.denuelle42.aiprompter.ui.theme.onSecondaryLightHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.onSecondaryLightMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.onSurfaceDark
import com.gmail.denuelle42.aiprompter.ui.theme.onSurfaceDarkHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.onSurfaceDarkMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.onSurfaceLight
import com.gmail.denuelle42.aiprompter.ui.theme.onSurfaceLightHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.onSurfaceLightMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.onSurfaceVariantDark
import com.gmail.denuelle42.aiprompter.ui.theme.onSurfaceVariantDarkHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.onSurfaceVariantDarkMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.onSurfaceVariantLight
import com.gmail.denuelle42.aiprompter.ui.theme.onSurfaceVariantLightHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.onSurfaceVariantLightMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.onTertiaryContainerDark
import com.gmail.denuelle42.aiprompter.ui.theme.onTertiaryContainerDarkHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.onTertiaryContainerDarkMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.onTertiaryContainerLight
import com.gmail.denuelle42.aiprompter.ui.theme.onTertiaryContainerLightHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.onTertiaryContainerLightMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.onTertiaryDark
import com.gmail.denuelle42.aiprompter.ui.theme.onTertiaryDarkHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.onTertiaryDarkMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.onTertiaryLight
import com.gmail.denuelle42.aiprompter.ui.theme.onTertiaryLightHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.onTertiaryLightMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.outlineDark
import com.gmail.denuelle42.aiprompter.ui.theme.outlineDarkHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.outlineDarkMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.outlineLight
import com.gmail.denuelle42.aiprompter.ui.theme.outlineLightHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.outlineLightMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.outlineVariantDark
import com.gmail.denuelle42.aiprompter.ui.theme.outlineVariantDarkHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.outlineVariantDarkMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.outlineVariantLight
import com.gmail.denuelle42.aiprompter.ui.theme.outlineVariantLightHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.outlineVariantLightMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.primaryContainerDark
import com.gmail.denuelle42.aiprompter.ui.theme.primaryContainerDarkHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.primaryContainerDarkMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.primaryContainerLight
import com.gmail.denuelle42.aiprompter.ui.theme.primaryContainerLightHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.primaryContainerLightMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.primaryDark
import com.gmail.denuelle42.aiprompter.ui.theme.primaryDarkHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.primaryDarkMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.primaryLight
import com.gmail.denuelle42.aiprompter.ui.theme.primaryLightHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.primaryLightMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.scrimDark
import com.gmail.denuelle42.aiprompter.ui.theme.scrimDarkHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.scrimDarkMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.scrimLight
import com.gmail.denuelle42.aiprompter.ui.theme.scrimLightHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.scrimLightMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.secondaryContainerDark
import com.gmail.denuelle42.aiprompter.ui.theme.secondaryContainerDarkHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.secondaryContainerDarkMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.secondaryContainerLight
import com.gmail.denuelle42.aiprompter.ui.theme.secondaryContainerLightHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.secondaryContainerLightMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.secondaryDark
import com.gmail.denuelle42.aiprompter.ui.theme.secondaryDarkHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.secondaryDarkMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.secondaryLight
import com.gmail.denuelle42.aiprompter.ui.theme.secondaryLightHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.secondaryLightMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.surfaceBrightDark
import com.gmail.denuelle42.aiprompter.ui.theme.surfaceBrightDarkHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.surfaceBrightDarkMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.surfaceBrightLight
import com.gmail.denuelle42.aiprompter.ui.theme.surfaceBrightLightHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.surfaceBrightLightMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.surfaceContainerDark
import com.gmail.denuelle42.aiprompter.ui.theme.surfaceContainerDarkHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.surfaceContainerDarkMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.surfaceContainerHighDark
import com.gmail.denuelle42.aiprompter.ui.theme.surfaceContainerHighDarkHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.surfaceContainerHighDarkMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.surfaceContainerHighLight
import com.gmail.denuelle42.aiprompter.ui.theme.surfaceContainerHighLightHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.surfaceContainerHighLightMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.surfaceContainerHighestDark
import com.gmail.denuelle42.aiprompter.ui.theme.surfaceContainerHighestDarkHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.surfaceContainerHighestDarkMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.surfaceContainerHighestLight
import com.gmail.denuelle42.aiprompter.ui.theme.surfaceContainerHighestLightHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.surfaceContainerHighestLightMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.surfaceContainerLight
import com.gmail.denuelle42.aiprompter.ui.theme.surfaceContainerLightHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.surfaceContainerLightMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.surfaceContainerLowDark
import com.gmail.denuelle42.aiprompter.ui.theme.surfaceContainerLowDarkHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.surfaceContainerLowDarkMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.surfaceContainerLowLight
import com.gmail.denuelle42.aiprompter.ui.theme.surfaceContainerLowLightHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.surfaceContainerLowLightMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.surfaceContainerLowestDark
import com.gmail.denuelle42.aiprompter.ui.theme.surfaceContainerLowestDarkHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.surfaceContainerLowestDarkMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.surfaceContainerLowestLight
import com.gmail.denuelle42.aiprompter.ui.theme.surfaceContainerLowestLightHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.surfaceContainerLowestLightMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.surfaceDark
import com.gmail.denuelle42.aiprompter.ui.theme.surfaceDarkHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.surfaceDarkMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.surfaceDimDark
import com.gmail.denuelle42.aiprompter.ui.theme.surfaceDimDarkHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.surfaceDimDarkMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.surfaceDimLight
import com.gmail.denuelle42.aiprompter.ui.theme.surfaceDimLightHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.surfaceDimLightMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.surfaceLight
import com.gmail.denuelle42.aiprompter.ui.theme.surfaceLightHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.surfaceLightMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.surfaceVariantDark
import com.gmail.denuelle42.aiprompter.ui.theme.surfaceVariantDarkHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.surfaceVariantDarkMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.surfaceVariantLight
import com.gmail.denuelle42.aiprompter.ui.theme.surfaceVariantLightHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.surfaceVariantLightMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.tertiaryContainerDark
import com.gmail.denuelle42.aiprompter.ui.theme.tertiaryContainerDarkHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.tertiaryContainerDarkMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.tertiaryContainerLight
import com.gmail.denuelle42.aiprompter.ui.theme.tertiaryContainerLightHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.tertiaryContainerLightMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.tertiaryDark
import com.gmail.denuelle42.aiprompter.ui.theme.tertiaryDarkHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.tertiaryDarkMediumContrast
import com.gmail.denuelle42.aiprompter.ui.theme.tertiaryLight
import com.gmail.denuelle42.aiprompter.ui.theme.tertiaryLightHighContrast
import com.gmail.denuelle42.aiprompter.ui.theme.tertiaryLightMediumContrast

private val lightScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,
    surfaceDim = surfaceDimLight,
    surfaceBright = surfaceBrightLight,
    surfaceContainerLowest = surfaceContainerLowestLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight,
)

private val darkScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark,
    surfaceDim = surfaceDimDark,
    surfaceBright = surfaceBrightDark,
    surfaceContainerLowest = surfaceContainerLowestDark,
    surfaceContainerLow = surfaceContainerLowDark,
    surfaceContainer = surfaceContainerDark,
    surfaceContainerHigh = surfaceContainerHighDark,
    surfaceContainerHighest = surfaceContainerHighestDark,
)

private val mediumContrastLightColorScheme = lightColorScheme(
    primary = primaryLightMediumContrast,
    onPrimary = onPrimaryLightMediumContrast,
    primaryContainer = primaryContainerLightMediumContrast,
    onPrimaryContainer = onPrimaryContainerLightMediumContrast,
    secondary = secondaryLightMediumContrast,
    onSecondary = onSecondaryLightMediumContrast,
    secondaryContainer = secondaryContainerLightMediumContrast,
    onSecondaryContainer = onSecondaryContainerLightMediumContrast,
    tertiary = tertiaryLightMediumContrast,
    onTertiary = onTertiaryLightMediumContrast,
    tertiaryContainer = tertiaryContainerLightMediumContrast,
    onTertiaryContainer = onTertiaryContainerLightMediumContrast,
    error = errorLightMediumContrast,
    onError = onErrorLightMediumContrast,
    errorContainer = errorContainerLightMediumContrast,
    onErrorContainer = onErrorContainerLightMediumContrast,
    background = backgroundLightMediumContrast,
    onBackground = onBackgroundLightMediumContrast,
    surface = surfaceLightMediumContrast,
    onSurface = onSurfaceLightMediumContrast,
    surfaceVariant = surfaceVariantLightMediumContrast,
    onSurfaceVariant = onSurfaceVariantLightMediumContrast,
    outline = outlineLightMediumContrast,
    outlineVariant = outlineVariantLightMediumContrast,
    scrim = scrimLightMediumContrast,
    inverseSurface = inverseSurfaceLightMediumContrast,
    inverseOnSurface = inverseOnSurfaceLightMediumContrast,
    inversePrimary = inversePrimaryLightMediumContrast,
    surfaceDim = surfaceDimLightMediumContrast,
    surfaceBright = surfaceBrightLightMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestLightMediumContrast,
    surfaceContainerLow = surfaceContainerLowLightMediumContrast,
    surfaceContainer = surfaceContainerLightMediumContrast,
    surfaceContainerHigh = surfaceContainerHighLightMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestLightMediumContrast,
)

private val highContrastLightColorScheme = lightColorScheme(
    primary = primaryLightHighContrast,
    onPrimary = onPrimaryLightHighContrast,
    primaryContainer = primaryContainerLightHighContrast,
    onPrimaryContainer = onPrimaryContainerLightHighContrast,
    secondary = secondaryLightHighContrast,
    onSecondary = onSecondaryLightHighContrast,
    secondaryContainer = secondaryContainerLightHighContrast,
    onSecondaryContainer = onSecondaryContainerLightHighContrast,
    tertiary = tertiaryLightHighContrast,
    onTertiary = onTertiaryLightHighContrast,
    tertiaryContainer = tertiaryContainerLightHighContrast,
    onTertiaryContainer = onTertiaryContainerLightHighContrast,
    error = errorLightHighContrast,
    onError = onErrorLightHighContrast,
    errorContainer = errorContainerLightHighContrast,
    onErrorContainer = onErrorContainerLightHighContrast,
    background = backgroundLightHighContrast,
    onBackground = onBackgroundLightHighContrast,
    surface = surfaceLightHighContrast,
    onSurface = onSurfaceLightHighContrast,
    surfaceVariant = surfaceVariantLightHighContrast,
    onSurfaceVariant = onSurfaceVariantLightHighContrast,
    outline = outlineLightHighContrast,
    outlineVariant = outlineVariantLightHighContrast,
    scrim = scrimLightHighContrast,
    inverseSurface = inverseSurfaceLightHighContrast,
    inverseOnSurface = inverseOnSurfaceLightHighContrast,
    inversePrimary = inversePrimaryLightHighContrast,
    surfaceDim = surfaceDimLightHighContrast,
    surfaceBright = surfaceBrightLightHighContrast,
    surfaceContainerLowest = surfaceContainerLowestLightHighContrast,
    surfaceContainerLow = surfaceContainerLowLightHighContrast,
    surfaceContainer = surfaceContainerLightHighContrast,
    surfaceContainerHigh = surfaceContainerHighLightHighContrast,
    surfaceContainerHighest = surfaceContainerHighestLightHighContrast,
)

private val mediumContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkMediumContrast,
    onPrimary = onPrimaryDarkMediumContrast,
    primaryContainer = primaryContainerDarkMediumContrast,
    onPrimaryContainer = onPrimaryContainerDarkMediumContrast,
    secondary = secondaryDarkMediumContrast,
    onSecondary = onSecondaryDarkMediumContrast,
    secondaryContainer = secondaryContainerDarkMediumContrast,
    onSecondaryContainer = onSecondaryContainerDarkMediumContrast,
    tertiary = tertiaryDarkMediumContrast,
    onTertiary = onTertiaryDarkMediumContrast,
    tertiaryContainer = tertiaryContainerDarkMediumContrast,
    onTertiaryContainer = onTertiaryContainerDarkMediumContrast,
    error = errorDarkMediumContrast,
    onError = onErrorDarkMediumContrast,
    errorContainer = errorContainerDarkMediumContrast,
    onErrorContainer = onErrorContainerDarkMediumContrast,
    background = backgroundDarkMediumContrast,
    onBackground = onBackgroundDarkMediumContrast,
    surface = surfaceDarkMediumContrast,
    onSurface = onSurfaceDarkMediumContrast,
    surfaceVariant = surfaceVariantDarkMediumContrast,
    onSurfaceVariant = onSurfaceVariantDarkMediumContrast,
    outline = outlineDarkMediumContrast,
    outlineVariant = outlineVariantDarkMediumContrast,
    scrim = scrimDarkMediumContrast,
    inverseSurface = inverseSurfaceDarkMediumContrast,
    inverseOnSurface = inverseOnSurfaceDarkMediumContrast,
    inversePrimary = inversePrimaryDarkMediumContrast,
    surfaceDim = surfaceDimDarkMediumContrast,
    surfaceBright = surfaceBrightDarkMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkMediumContrast,
    surfaceContainerLow = surfaceContainerLowDarkMediumContrast,
    surfaceContainer = surfaceContainerDarkMediumContrast,
    surfaceContainerHigh = surfaceContainerHighDarkMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkMediumContrast,
)

private val highContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkHighContrast,
    onPrimary = onPrimaryDarkHighContrast,
    primaryContainer = primaryContainerDarkHighContrast,
    onPrimaryContainer = onPrimaryContainerDarkHighContrast,
    secondary = secondaryDarkHighContrast,
    onSecondary = onSecondaryDarkHighContrast,
    secondaryContainer = secondaryContainerDarkHighContrast,
    onSecondaryContainer = onSecondaryContainerDarkHighContrast,
    tertiary = tertiaryDarkHighContrast,
    onTertiary = onTertiaryDarkHighContrast,
    tertiaryContainer = tertiaryContainerDarkHighContrast,
    onTertiaryContainer = onTertiaryContainerDarkHighContrast,
    error = errorDarkHighContrast,
    onError = onErrorDarkHighContrast,
    errorContainer = errorContainerDarkHighContrast,
    onErrorContainer = onErrorContainerDarkHighContrast,
    background = backgroundDarkHighContrast,
    onBackground = onBackgroundDarkHighContrast,
    surface = surfaceDarkHighContrast,
    onSurface = onSurfaceDarkHighContrast,
    surfaceVariant = surfaceVariantDarkHighContrast,
    onSurfaceVariant = onSurfaceVariantDarkHighContrast,
    outline = outlineDarkHighContrast,
    outlineVariant = outlineVariantDarkHighContrast,
    scrim = scrimDarkHighContrast,
    inverseSurface = inverseSurfaceDarkHighContrast,
    inverseOnSurface = inverseOnSurfaceDarkHighContrast,
    inversePrimary = inversePrimaryDarkHighContrast,
    surfaceDim = surfaceDimDarkHighContrast,
    surfaceBright = surfaceBrightDarkHighContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkHighContrast,
    surfaceContainerLow = surfaceContainerLowDarkHighContrast,
    surfaceContainer = surfaceContainerDarkHighContrast,
    surfaceContainerHigh = surfaceContainerHighDarkHighContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkHighContrast,
)

@Immutable
data class ColorFamily(
    val color: Color,
    val onColor: Color,
    val colorContainer: Color,
    val onColorContainer: Color
)

val unspecified_scheme = ColorFamily(
    Color.Unspecified, Color.Unspecified, Color.Unspecified, Color.Unspecified
)

@Composable
fun VerifaiTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable() () -> Unit
) {
  val colorScheme = when {
      dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
          val context = LocalContext.current
          if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
      }
      
      darkTheme -> lightScheme
      else -> lightScheme
  }

    // 2. ADD THIS BLOCK to handle the Status Bar Icons
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window

            // This controller lets us change the icon colors
            val insetsController = WindowCompat.getInsetsController(window, view)

            // TRUE = Dark Icons (for light background)
            // FALSE = Light Icons (for dark background)

            // Since light mode is enforced
            insetsController.isAppearanceLightStatusBars = true
        }
    }

  MaterialTheme(
    colorScheme = colorScheme,
    typography = AppTypography,
    content = content
  )
}


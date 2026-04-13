package com.jek.kalkulatornilaiproduk.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = SageMist,
    onPrimary = ForestDark,
    primaryContainer = DeepMoss,
    onPrimaryContainer = SandCream,
    secondary = SandCream,
    onSecondary = ForestDark,
    secondaryContainer = Color(0xFF4B5B44),
    onSecondaryContainer = SoftIvory,
    tertiary = MintSurface,
    onTertiary = ForestDark,
    background = PineNight,
    onBackground = SoftIvory,
    surface = ForestSurface,
    onSurface = SoftIvory,
    surfaceVariant = ForestSurfaceHigh,
    onSurfaceVariant = Color(0xFFD0D8CA),
    outline = ForestOutline
)

private val LightColorScheme = lightColorScheme(
    primary = OliveGreen,
    onPrimary = Color.White,
    primaryContainer = SageMist,
    onPrimaryContainer = ForestDark,
    secondary = WarmLeaf,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFE6EDD8),
    onSecondaryContainer = ForestDark,
    tertiary = SandCream,
    onTertiary = ForestDark,
    background = SandCream,
    surface = SoftIvory,
    surfaceVariant = MintSurface,
    onBackground = ForestDark,
    onSurface = CocoaBrown,
    onSurfaceVariant = Color(0xFF5E6659),
    outline = Color(0xFF7A8475)
)

@Composable
fun KalkulatorNilaiProdukTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

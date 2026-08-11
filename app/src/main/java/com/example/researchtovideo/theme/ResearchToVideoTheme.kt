package com.example.researchtovideo.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val YellowAccent = Color(0xFFF5C518)
val DarkBackground = Color(0xFF1A1A2E)
val LightBackground = Color(0xFFFAFAF8)
val DarkSurface = Color(0xFF24243A)
val LightSurface = Color(0xFFFFFFFF)

private val DarkColors = darkColorScheme(
    primary = YellowAccent,
    secondary = Color(0xFF9DA6B8),
    background = DarkBackground,
    surface = DarkSurface,
)

private val LightColors = lightColorScheme(
    primary = Color(0xFFB8860B),
    secondary = Color(0xFF4A5568),
    background = LightBackground,
    surface = LightSurface,
)

@Composable
fun ResearchToVideoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        content = content
    )
}

package org.robert.project

// In composeApp/src/commonMain/kotlin/org/robert/project/ui/theme/Theme.kt

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.Typography // If you define custom typography
// import androidx.compose.material3.Shapes // If you define custom shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// Define your light color scheme using the colors from Color.kt
private val LightColorScheme = lightColorScheme(
    primary = PrimaryLight,
    onPrimary = OnPrimaryLight,
    primaryContainer = PrimaryContainerLight,
    onPrimaryContainer = OnPrimaryContainerLight,
    secondary = PurpleGrey80, // Replace with your actual secondary colors
    onSecondary = Color.Black, // Replace
    tertiary = Pink80,         // Replace
    onTertiary = Color.Black,  // Replace
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    // You can define other colors like error, surfaceVariant etc.
    // Use the Material Theme Builder (https://m3.material.io/theme-builder)
    // to generate a full scheme based on a few key colors.
)

// Define your dark color scheme
private val DarkColorScheme = darkColorScheme(
    primary = PrimaryDark,
    onPrimary = OnPrimaryDark,
    primaryContainer = PrimaryContainerDark,
    onPrimaryContainer = OnPrimaryContainerDark,
    secondary = PurpleGrey40, // Replace
    onSecondary = Color.White,  // Replace
    tertiary = Pink40,          // Replace
    onTertiary = Color.White,   // Replace
    background = Color(0xFF1C1B1F),
    surface = Color(0xFF1C1B1F),
    onBackground = Color(0xFFE6E1E5),
    onSurface = Color(0xFFE6E1E5),
)

// You can also define Typography and Shapes if needed
// For now, we'll use Material 3 defaults by not overriding them explicitly
// val AppTypography = Typography(...)
// val AppShapes = Shapes(...)

@Composable
fun MAnimeTheme( // Choose a name for your theme, e.g., AppNameTheme
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            // Set status bar color from the theme
            window.statusBarColor = colorScheme.primary.toArgb()
            // Set status bar icon colors (light/dark)
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        // typography = AppTypography, // Uncomment if you define custom Typography
        // shapes = AppShapes,       // Uncomment if you define custom Shapes
        content = content
    )
}
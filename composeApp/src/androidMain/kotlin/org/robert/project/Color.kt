package org.robert.project

// In composeApp/src/commonMain/kotlin/org/robert/project/ui/theme/Color.kt
// (or your chosen KMP source set)

import androidx.compose.ui.graphics.Color

// Light Theme Colors
val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

// Dark Theme Colors (can be the same or different)
val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

// You can define more specific colors for your theme:
// Primary, Secondary, Tertiary
val PrimaryLight = Color(0xFF6750A4) // Example Primary for Light Theme
val OnPrimaryLight = Color(0xFFFFFFFF)
val PrimaryContainerLight = Color(0xFFEADDFF)
val OnPrimaryContainerLight = Color(0xFF21005D)
// ... add all required M3 colors (surface, background, error, etc.)

val PrimaryDark = Color(0xFFD0BCFF) // Example Primary for Dark Theme
val OnPrimaryDark = Color(0xFF381E72)
val PrimaryContainerDark = Color(0xFF4F378B)
val OnPrimaryContainerDark = Color(0xFFEADDFF)
// ... add all required M3 colors for dark theme

// You can use the Material Theme Builder to generate a full palette:
// https://m3.material.io/theme-builder
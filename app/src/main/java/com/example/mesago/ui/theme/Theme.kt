package com.example.mesago.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val MesaGoColorScheme = lightColorScheme(
    primary = MesaGoRed,
    secondary = MesaGoGray,
    tertiary = MesaGoRedDark,

    background = MesaGoBackground,
    surface = MesaGoWhite,

    onPrimary = MesaGoWhite,
    onSecondary = MesaGoBlack,
    onTertiary = MesaGoWhite,

    onBackground = MesaGoBlack,
    onSurface = MesaGoBlack
)

@Composable
fun MesaGoTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = MesaGoColorScheme,
        typography = Typography,
        content = content
    )
}
package com.techvipin130524.simplecalc.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = GreenOperator,
    secondary = DarkAction,
    tertiary = DarkButton,
    background = DarkBg,
    surface = DarkBg,
    onPrimary = DarkOnBg,
    onSecondary = DarkOnBg,
    onTertiary = DarkOnBg,
    onBackground = DarkOnBg,
    onSurface = DarkOnBg
)

private val LightColorScheme = lightColorScheme(
    primary = GreenOperator,
    secondary = LightAction,
    tertiary = LightButton,
    background = LightBg,
    surface = LightBg,
    onPrimary = LightOnBg,
    onSecondary = LightOnBg,
    onTertiary = LightOnBg,
    onBackground = LightOnBg,
    onSurface = LightOnBg
)

@Composable
fun SimpleCalcTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is disabled by default to keep the design's look
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
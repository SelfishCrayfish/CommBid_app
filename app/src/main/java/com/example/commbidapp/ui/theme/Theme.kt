package com.example.commbidapp.ui.theme

import androidx.compose.foundation.Canvas
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.commbidapp.R
import kotlin.math.hypot

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

val RegularFont = FontFamily(
    Font(R.font.aleo_regular)
)

@Composable
fun DiagonalStripesBackground(
    color1: Color,
    color2: Color,
    stripeWidth: Float = 20f,
    spacing: Float = 40f
) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val totalWidth = size.width
        val totalHeight = size.height
        val diagonalLength = hypot(totalWidth.toDouble(), totalHeight.toDouble()).toFloat()

        var currentOffset = -diagonalLength / 2
        while (currentOffset < diagonalLength * 2) {
            drawLine(
                color = if ((currentOffset / spacing).toInt() % 2 == 0) color1 else color2,
                start = androidx.compose.ui.geometry.Offset(currentOffset - totalHeight, 0f),
                end = androidx.compose.ui.geometry.Offset(currentOffset, totalHeight),
                strokeWidth = stripeWidth
            )
            currentOffset += stripeWidth
        }
    }
}

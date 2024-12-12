package dev.maruffirdaus.w3_androidstudygroup.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import dev.maruffirdaus.w3_androidstudygroup.R

val sofiaFamily = FontFamily(
    Font(R.font.sofia_regular, FontWeight.Normal)
)

val onestFamily = FontFamily(
    Font(R.font.onest_black, FontWeight.Black),
    Font(R.font.onest_bold, FontWeight.Bold),
    Font(R.font.onest_extrabold, FontWeight.ExtraBold),
    Font(R.font.onest_extralight, FontWeight.ExtraLight),
    Font(R.font.onest_light, FontWeight.Light),
    Font(R.font.onest_medium, FontWeight.Medium),
    Font(R.font.onest_regular, FontWeight.Normal),
    Font(R.font.onest_semibold, FontWeight.SemiBold),
    Font(R.font.onest_thin, FontWeight.Thin)
)

val baseline = Typography()

val Typography = Typography(
    displayLarge = baseline.displayLarge.copy(fontFamily = onestFamily),
    displayMedium = baseline.displayMedium.copy(fontFamily = onestFamily),
    displaySmall = baseline.displaySmall.copy(fontFamily = onestFamily),
    headlineLarge = baseline.headlineLarge.copy(fontFamily = onestFamily),
    headlineMedium = baseline.headlineMedium.copy(fontFamily = onestFamily),
    headlineSmall = baseline.headlineSmall.copy(fontFamily = onestFamily),
    titleLarge = baseline.titleLarge.copy(fontFamily = onestFamily),
    titleMedium = baseline.titleMedium.copy(fontFamily = onestFamily),
    titleSmall = baseline.titleSmall.copy(fontFamily = onestFamily),
    bodyLarge = baseline.bodyLarge.copy(fontFamily = onestFamily),
    bodyMedium = baseline.bodyMedium.copy(fontFamily = onestFamily),
    bodySmall = baseline.bodySmall.copy(fontFamily = onestFamily),
    labelLarge = baseline.labelLarge.copy(fontFamily = onestFamily),
    labelMedium = baseline.labelMedium.copy(fontFamily = onestFamily),
    labelSmall = baseline.labelSmall.copy(fontFamily = onestFamily)
)
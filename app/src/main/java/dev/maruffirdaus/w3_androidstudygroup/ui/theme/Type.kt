package dev.maruffirdaus.w3_androidstudygroup.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import dev.maruffirdaus.w3_androidstudygroup.R

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val sofia = GoogleFont("Sofia")

val sofiaFamily = FontFamily(
    Font(googleFont = sofia, fontProvider = provider)
)

val onest = GoogleFont("Onest")

val onestFamily = FontFamily(
    Font(googleFont = onest, fontProvider = provider)
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